package coya.quotes

import coya.models.{House, ProductQuote, User}

object HouseProcessor extends ProductProcessor[House] {
  val BasePremiumMultiplier: Double = 0.03
  private val HousePriceSurchargeLimit = 10000000

  override def quote(u: User, p: Seq[House]): Seq[ProductQuote] = ???

  override def calculatePremium(product: House): BigDecimal = ???
}
