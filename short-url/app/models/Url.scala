package models 
import anorm._
import play.api.db.DB
import play.api.Play.current

case class Url(id: Option[Long], address: String)

object Url {

  val parser: RowParser[Url] = Macro.namedParser[Url]

  // find the url id from the string given
  def find(url: Url): Option[Long] = {
    DB.withConnection { implicit c =>
    val result = SQL("SELECT id FROM Url WHERE address = {address}").on(
      'address -> url.address
      ).apply
       .headOption

      result match {
        case Some(row) => Some(row[Long]("id"))
        case None => None
      }

    }
  }

  // insert a new entry into the database if the url string does not already exist
  def insert(url: Url): Option[Long] = {
    DB.withConnection { implicit c =>
    SQL("insert into Url(address) values ({address})").on(
      'address -> url.address
      ).executeInsert()
    }
  }
}
