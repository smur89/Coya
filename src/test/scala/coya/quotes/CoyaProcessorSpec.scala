package coya.quotes

import coya.models._
import org.scalatest._

class CoyaProcessorSpec extends FlatSpec with Matchers {
  val goodAddress = Address(1, 10)
  val badAddress = Address(1, 500)

  val userOne = User(1, goodAddress, 10)
  val userTwo = User(2, badAddress, 150)

  val funBike = Bicycle(1, BigDecimal(1000), 18)
  val coolHouse = House(2, BigDecimal(1000000), goodAddress, 40)

  /*
   1,000,000 * // house value
   0.03 * // house base premium value
   0.07 * // house risk surcharge
   0.3 // user risk surcharge
   = 630 € per year
   */
  "userOne with coolHouse" should "receive a good offer" in {
    CoyaProcessor.priceFor(userOne, coolHouse) shouldEqual Some(BigDecimal(630))
  }

  /*
   1000 * // bike value
   0.10 * // bike base premium value
   (18 * 0.08) * // gears surcharge
   1 // user risk surcharge
   = 144 € per year

   Given that userTwo has a risk value of more than 150 and the total
   premium is bigger than 100 €, we won't offer him insurance.
   */
  "userTwo with funBike" should "be denied" in {
    CoyaProcessor.priceFor(userTwo, funBike) shouldEqual None
  }
}