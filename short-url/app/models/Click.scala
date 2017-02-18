package models
import anorm._
import play.api.db.DB
import java.util.Date
import play.api.Play.current

case class Click(id: Option[Long], time: Date, ip: String, address: Option[Long])

object Click {

  val parser: RowParser[Click] = Macro.namedParser[Click]

  // insert the click information into the database
  def insert(click: Click): Option[Long] = {
    DB.withConnection { implicit c =>
    SQL("insert into Click(time, ip, address) values ({time}, {ip}, {address})").on(
      'time -> click.time,
      'ip -> click.ip,
      'address -> click.address
      ).executeInsert()
    }
  }
}
