package models 
import anorm._
import play.api.db.DB
import play.api.Play.current

case class Url(id: Option[Long], address: String)

object Url {

  val parser: RowParser[Url] = Macro.namedParser[Url]

  def insert(url: Url): Option[Long] = {
    DB.withConnection { implicit c =>
    SQL("insert into Url(address) values ({address})").on(
      'address -> url.address
      ).executeInsert()
    }
  }
}
