package services
import javax.inject._

// object encode extends (Int => String) {
//   def apply(url: Int): String = {
//     "test"
//   }
// }
//
// object decode extends (String => Int) {
//   def apply(url: String): Int = {
//     1
//   }
// }

trait Short {
  def encode(url: Int): String
  def decode(url: String): Int
}

@Singleton
class CompleteShort extends Short {  
  private val alphabets = "abcd"

  override def encode(url: Int): String = {
    "test"
  }

  override def decode(url: String): Int = {
    1
  }
}
