package coya.quotes

import coya.models.{Product, ProductQuote, User}

trait ProductProcessor[T <: Product] {
  def BasePremiumMultiplier: Double

  def calculatePremium(product: T): BigDecimal = product.value * BasePremiumMultiplier

  def quote(u: User, p: Seq[T]): Seq[ProductQuote]
}