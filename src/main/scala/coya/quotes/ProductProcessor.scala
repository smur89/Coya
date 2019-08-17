package coya.quotes

import coya.models.{Product, ProductQuote, User}

trait ProductProcessor[T <: Product] {
  def BasePremiumMultiplier: Double

  def userSurcharge(u: User) = u match {
    case u if u.risk < 20 => Some(0.3)
    case u if u.risk < 200 => Some(1d)
    case u if u.risk < 500 => Some(3d)
    case _ => None
  }

  def calculatePremium(u: User, product: T): Option[BigDecimal] = userSurcharge(u).map(_ * product.value * BasePremiumMultiplier)

  def quote(u: User, p: Seq[T]): Seq[ProductQuote]
}