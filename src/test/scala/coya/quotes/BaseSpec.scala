package coya.quotes

import coya.models._
import org.scalatest.{FlatSpec, Matchers}

trait BaseSpec extends FlatSpec with Matchers{

  val goodAddress = Address(1, 10)
  val badAddress = Address(1, 500)

  val userOne = User(1, goodAddress, 10)
  val userTwo = User(2, badAddress, 150)
  val highRiskUser = User(3, goodAddress, 500)

  val funBike = Bicycle(1, BigDecimal(1000), 18)

  val coolHouse = House(2, BigDecimal(1000000), goodAddress, 40)
  val chillHouse = House(2, BigDecimal(10000001), goodAddress, 40)
  val ganglandHouse = House(2, BigDecimal(10000001), badAddress, 40)
  val hobbitHouse = House(2, BigDecimal(10000001), badAddress, 30)

  val cleanBanana = Banana(1, BigDecimal(0.50), 0)
  val rottenBanana = Banana(1, BigDecimal(0.50), 30)
  val justRightBanana = Banana(1, BigDecimal(0.50), 4)
}
