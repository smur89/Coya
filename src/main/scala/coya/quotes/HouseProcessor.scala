package coya.quotes

import coya.models.{House, ProductQuote, User}

object HouseProcessor extends ProductProcessor[House] {
  val BasePremiumMultiplier: Double = 0.03

  private val HousePriceSurchargeLimit = 10000000
  private val InsurableSizeRange = 30 to 1000

  def houseRiskSurcharge(u: House): Option[Double] = u match {
    case u if u.address.locationRisk < 100 => Some(0.7)
    case u if u.address.locationRisk < 300 => Some(1d)
    case u if u.address.locationRisk < 501 => Some(2.6)
    case _ => None
  }

  override def quote(u: User, h: Seq[House]): Seq[ProductQuote] = {
    val highValueSurcharge = if (h.exists(_.value > HousePriceSurchargeLimit)) 1.15 else 1
    h.map(house => {
      ProductQuote(house, if (isInsurable(house)) calculatePremium(u, house).map(_ * highValueSurcharge) else None)
    })
  }

  override def calculatePremium(u: User, house: House): Option[BigDecimal] = for {
    surcharge <- houseRiskSurcharge(house)
    basePremium <- super.calculatePremium(u, house)
  } yield surcharge * basePremium

  def isInsurable(house: House): Boolean = InsurableSizeRange.contains(house.size)
}
