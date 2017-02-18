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

  def index = Action {
    Ok(views.html.index())
  }

  val urlForm = Form (
    single(
        "address" -> text
      )
  )

  def cat = Action { implicit request =>
    println(s"query string: ${request.body}")
    val address = urlForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index()),
      address => {
          val time = new Date()
          val ip = request.remoteAddress
          val url = Url.insert(Url(None, address))
          // val ip = "172.19.0.2"
          // val url = Some(1L)
          val click = Click.insert(Click(None, time, ip, url))
          val encoded = short.encode(url)
          println(encoded)
          Redirect(routes.HomeController.index)
      }
    )
    Redirect(routes.HomeController.index)
  }

  def group = Action { implicit request => 
    DB.withConnection { implicit c =>
      val data = SQL("SELECT json_build_object('ip', c.ip, 'data', json_agg(json_build_object('address', u.address, 'time', c.time)))" + 
        "FROM click c LEFT JOIN url u ON c.address=u.id GROUP BY c.ip;")().map {
          case Row(x: Any) => x
        }
      Ok(Json.toJson(data.head.toString))
    }
  }
}
