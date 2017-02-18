package services
import javax.inject._

trait Short {
  def encode(url: Option[Long]): String
  def helper(acc: List[Long], cur: Long): List[Long]
  def decode(url: String): Option[Long]
}

@Singleton
class CompleteShort extends Short {  
  private val alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

  override def encode(url: Option[Long]): String = {
    val start = url.get
    val help = helper(List[Long](), start)
    help.map(x => alphabets.charAt(x.toInt)).mkString
  }

  override def helper(acc:List[Long], cur: Long): List[Long] = {
    val remainder = cur % 62
    if (cur < 62) remainder +: acc
    else helper(remainder +: acc, cur / 62)
  }

  override def decode(url: String): Option[Long] = {
    val result = url.foldLeft(0)((acc, cur) => acc + alphabets.indexOf(cur) * math.pow(62, url.size - url.indexOf(cur) - 1).toInt)
    Some(result.toLong)
  }
}
