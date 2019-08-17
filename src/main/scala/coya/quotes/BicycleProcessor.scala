package coya.quotes

import coya.models.{Bicycle, ProductQuote, User}

object BicycleProcessor extends ProductProcessor[Bicycle] {
  val BasePremiumMultiplier: Double = 0.10

  private val GearsSurcharge = 0.08
  private val MaxUserRisk: Int = 150
  private val MaxPremiumForRiskyUsers = BigDecimal(100)

  override def quote(u: User, p: Seq[Bicycle]): Seq[ProductQuote] = {
    p.map(bicycle => {
      val premium = calculatePremium(bicycle)
      ProductQuote(bicycle, if (premium > MaxPremiumForRiskyUsers && u.risk >= MaxUserRisk) None else Some(premium))
    })
  }

  override def calculatePremium(product: Bicycle): BigDecimal = super.calculatePremium(product) + (product.gears * GearsSurcharge)
}
