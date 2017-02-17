package controllers

import models.{Url, Click}
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import java.util.Date

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
}
