package models
import anorm._
import play.api.db.DB
import java.util.Date
import play.api.Play.current

case class Click(id: Option[Long], time: Date, ip: String, address: Option[Long])

object Click {

  val parser: RowParser[Click] = Macro.namedParser[Click]

  def insert(click: Click): Option[Long] = {
    DB.withConnection { implicit c =>
    SQL("insert into Click(time, ip, address) values ({time}, {ip}, {address})").on(
      'time -> click.time,
      'ip -> click.ip,
      'address -> click.address
      ).executeInsert()
    }
  }

  // def group(): JSON = {
  //   DB.withConnection { implicit c =>
  //     SQL("SELECT json_build_object('ip', c.ip, 'data', json_agg(json_build_object('address', u.address, 'time', c.time))) FROM click c LEFT JOIN url u ON c.address=u.id GROUP BY c.ip;")
  //   }
  //
  // }
}
