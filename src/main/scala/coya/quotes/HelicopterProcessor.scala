package coya.quotes

import coya.models.{Helicopter, ProductQuote, User}

object HelicopterProcessor extends ProductProcessor[Helicopter] {
  val BasePremiumMultiplier: Double = 0.05

  override def quote(u: User, p: Seq[Helicopter]): Seq[ProductQuote] = ???

  override def calculatePremium(product: Helicopter): BigDecimal = ???
}
