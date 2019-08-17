package coya.quotes

import coya.models.{Banana, ProductQuote, User}

object BananaProcessor extends ProductProcessor[Banana] {
  val BasePremiumMultiplier: Double = 1.15

  private val MaxUserRisk: Int = 200
  private val InsurableBananaRange: Range.Inclusive = 3 to 12

  private def isInsurable(u: User, banana: Banana): Boolean = {
    u.risk < MaxUserRisk && InsurableBananaRange.contains(banana.blackSpots)
  }

  override def quote(u: User, p: Seq[Banana]): Seq[ProductQuote] = p.map(banana => {
    ProductQuote(banana, if (isInsurable(u, banana)) calculatePremium(u, banana) else None)
  })

}
