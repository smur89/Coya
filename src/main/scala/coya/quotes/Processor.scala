package coya.quotes

trait Processor {
  def priceFor(u: User): Maybe[BigDecimal]
}

object CoyaProcessor extends Processor {
  def priceFor(u: User, p: Product): Maybe[BigDecimal] = ???
}
