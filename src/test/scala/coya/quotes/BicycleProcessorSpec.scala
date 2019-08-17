package coya.quotes

class BicycleProcessorSpec extends BaseSpec {

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
    CoyaProcessor.priceFor(userTwo, List(funBike)) shouldEqual None
  }

  /*
 1000 * // bicycle value
 0.10 + // bicycle base premium value
 18 * 0.08 // gears surcharge
 0.3 // user risk surcharge
 = 31.44€ per year
 */
  "userOne with funBike" should "receive a good offer" in {
    CoyaProcessor.priceFor(userOne, List(funBike)) shouldEqual Some(BigDecimal(31.44))
  }
}
