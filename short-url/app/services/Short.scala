package services
import javax.inject._

trait Short {
  def encode(url: Option[Long]): String
  def decode(url: String): Option[Long]
}

@Singleton
class CompleteShort extends Short {  
  private val alphabets = "abcd"

  override def encode(url: Option[Long]): String = {
    "test"
  }

  override def decode(url: String): Option[Long] = {
    Some(1L)
  }
}
