package coya.quotes

import coya.models._

trait Processor {
  def priceFor(u: User, p: Seq[Product]): Option[BigDecimal]
}

object CoyaProcessor extends Processor {

  def priceFor(u: User, p: Seq[Product]): Option[BigDecimal] = {
    val quotes = getQuotesPerProduct(u, p)
    val decimals = quotes.flatMap(_.quote)
    if (decimals.nonEmpty) Some(decimals.sum) else None
  }

  private def getQuotesPerProduct[T <: Product](u: User, p: Seq[Product]): Seq[ProductQuote] = {
    val (houses: Seq[House], others) = p.partition {
      case _: House => true
      case _ => false
    }
    HouseProcessor.quote(u, houses) ++ others.foldLeft(Seq.empty[ProductQuote])((quotes, product) => quotes ++ {
      product match {
        case banana: Banana => BananaProcessor.quote(u, Seq(banana))
        case bicycle: Bicycle => BicycleProcessor.quote(u, Seq(bicycle))
        case helicopter: Helicopter => HelicopterProcessor.quote(u, Seq(helicopter))
        case house: House => HouseProcessor.quote(u, Seq(house))
      }
    })
  }
}
