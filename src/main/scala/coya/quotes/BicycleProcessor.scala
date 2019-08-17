package coya.quotes

import coya.models.{Bicycle, ProductQuote, User}

object BicycleProcessor extends ProductProcessor[Bicycle] {
  val BasePremiumMultiplier: Double = 0.10

  private val GearsSurcharge = 0.08
  private val MaxUserRisk: Int = 150
  private val MaxPremiumForRiskyUsers = BigDecimal(100)

  override def quote(u: User, p: Seq[Bicycle]): Seq[ProductQuote] = {
    p.map(bicycle => {
      val premium = calculatePremium(u, bicycle)
      ProductQuote(bicycle, if (premium > Some(MaxPremiumForRiskyUsers) && u.risk >= MaxUserRisk) None else premium)
    })
  }

  override def calculatePremium(u: User, product: Bicycle) = super.calculatePremium(u, product).map(_ + (product.gears * GearsSurcharge))
}
