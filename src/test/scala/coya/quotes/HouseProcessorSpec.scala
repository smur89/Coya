package coya.quotes

import org.scalatest._

class HouseProcessorSpec extends BaseSpec {

  /*
   100,000 * // house value
   0.03 * // house base premium value
   0.7 * // house risk surcharge
   0.3 // user risk surcharge
   = 630 € per year
   */
  "userOne with coolHouse" should "receive a good offer" in {
    CoyaProcessor.priceFor(userOne, List(coolHouse)) shouldEqual Some(BigDecimal(630))
  }

  /*
   100,000 * // house value                         10,000,001 * // house value
   0.03 * // house base premium value               0.03 * // house base premium value
   0.7 * // house risk surcharge                    0.7 * // house risk surcharge
   0.3 * // user risk surcharge                     0.3 // user risk surcharge
   1.15 // Expensive House surcharge                1.15 // Expensive House surcharge
   = 724.50 € per year                              = 772,450.01 € per year

   = 73174.507245 € per year
   */
  "userOne with chillHouse" should "receive a good offer" in {
    CoyaProcessor.priceFor(userOne, List(coolHouse, chillHouse)) shouldEqual Some(BigDecimal(73174.507245))
  }

  /*
   1,000,000 * // house value
   0.03 * // house base premium value
   No Offer * // house risk surcharge
   = None
 */
  "userOne with ganglandHouse" should "receive no offer" in {
    CoyaProcessor.priceFor(userOne, List(ganglandHouse)) shouldEqual None
  }


  "userOne with small house" should "receive no offer" in {
    CoyaProcessor.priceFor(userOne, List(hobbitHouse)) shouldEqual None
  }

}
