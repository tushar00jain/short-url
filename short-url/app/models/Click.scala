package models
import java.sql.Timestamp

case class Click(id: Int, time: Timestamp, ip: String, url: Int)

object Click {

}
