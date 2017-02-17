package models
import anorm._
import play.api.db.DB
// import java.sql.Timestamp
import java.util.Date
import play.api.Play.current

case class Click(id: Option[Long], time: Date, ip: String, address: Int)

object Click {

  val parser: RowParser[Click] = Macro.namedParser[Click]

  def insert(click: Click): Unit = {
    DB.withConnection { implicit c =>
    SQL("insert into Click(time, ip, address) values ({time}, {ip}, {address})").on(
      'time -> click.time,
      'ip -> click.ip,
      'address -> click.address
      ).executeInsert()
    }
  }
}
