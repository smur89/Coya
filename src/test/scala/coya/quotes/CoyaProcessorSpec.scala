package coya.quotes

class CoyaProcessorSpec extends BaseSpec  {


  /*
   1000 * // bike value
   0.10 * // bike base premium value
   (18 * 0.08) * // gears surcharge
   1 // user risk surcharge
   = 144 € per year

   Given that userTwo has a risk value of more than 150 and the total
   premium is bigger than 100 €, we won't offer him insurance.
   */
  "userTwo with all products" should "be denied" in {
    CoyaProcessor.priceFor(userTwo, List(funBike, ganglandHouse, justRightBanana)) shouldEqual None
  }
}
