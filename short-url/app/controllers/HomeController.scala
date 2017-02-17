package controllers

import models.Url
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

// import services.encode
// import services.decode

import services.Short

@Singleton
class HomeController @Inject() (short: Short) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  val urlForm = Form (
    mapping(
        "url" -> text
      )(Url.apply)(Url.unapply)
  )

  def cat = Action { implicit request =>
    println(s"query string: ${request.body}")
    val test = short.encode(1)
    println(test)
    val url = urlForm.bindFromRequest.get
    Redirect(routes.HomeController.index)
  }

}
