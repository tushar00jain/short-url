package controllers

import models.{Url, Click}
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import java.util.Date
import play.api.db.DB
import anorm._
import play.api.libs.json.Json
import play.api.Play.current

import services.Short

@Singleton
class HomeController @Inject() (short: Short) extends Controller {

  // index page of the application
  def index = Action {
    Ok(views.html.index())
  }

  // show clicks grouped by ip addresses
  def showGroup = Action { implicit request =>
    Ok(views.html.group())
  }

  // form to get the url to be shortened
  val urlForm = Form (
    single(
        "address" -> text
      )
  )

  // gets the url from the form and inserts data into the database
  def cat = Action { implicit request =>
    urlForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index()),
      address => {
          // create a new database entry for the url
          // if it already exists, find the id from the given url
          val time = new Date()
          val ip = request.remoteAddress
          val url = try {
            Url.insert(Url(None, address))
          } catch {
            case _ : Throwable => Url.find((Url(None, address)))
          }
          val click = Click.insert(Click(None, time, ip, url))

          // shorten the url
          val encoded = short.encode(url)
          Ok("localhost:9000/rid/" + encoded)
      }
    )
  }

  // converts the short url to original url and redirects 
  def rid(url: String) = Action { implicit request => 
    // decode the short url and find the url from the index
    DB.withConnection { implicit c =>
      val id = short.decode(url)
      val result = SQL("SELECT address FROM Url WHERE id = {id}").on(
        'id -> id
        ).apply
         .headOption

      result match {
        // redirect to external link
        case Some(row) => Redirect("http://" + row[String]("address"))
        case None => BadRequest("Something Bad Happened")
      }
    }
  }

  def group = Action { implicit request => 
    DB.withConnection { implicit c =>
      // returns json as rows: each row contains information about a single ip address
      val data = SQL("SELECT json_build_object('ip', c.ip, 'data', json_agg(json_build_object('address', u.address, 'time', c.time)))" + 
        "FROM click c LEFT JOIN url u ON c.address=u.id GROUP BY c.ip;")().map {
          case Row(x: Any) => x
        }
      Ok(data.head.toString).as("application/json")
    }
  }
}
