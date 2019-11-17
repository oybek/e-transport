package io.github.oybek.catpc

import org.scalatest.{FlatSpec, Matchers}

class MessageAnalyzerSpec extends FlatSpec with Matchers {
  def priceRangeDefiner(text:String): (Long, Long) = {
    val exactPrice = "за[ ]+\\d+".r
      .findFirstIn(text)
      .map(_.split(' ')(1).toLong)
    exactPrice match {
      case Some(value) => {
        val coeff = value * 0.1
        ((value - coeff).toLong, (value + coeff).toLong)
      }
      case None => {
        val min = "от[ ]+\\d+".r
          .findFirstIn(text)
          .map(_.split(' ')(1).toLong)
          .getOrElse(0L)
        val max = "до[ ]+\\d+".r
          .findFirstIn(text)
          .map(_.split(' ')(1).toLong)
          .getOrElse(Long.MaxValue)
        (min, max)
      }
    }
  }

  "priceRanger" should "return right price" in {
    priceRangeDefiner("проц за 5000") should be (4500,5500)
    priceRangeDefiner("проц до 5000") should be (0,5000)
    priceRangeDefiner("проц от 4000 до 5000") should be (4000,5000)
  }

}
