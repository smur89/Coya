package coya.quotes

class CoyaProcessorSpec extends BaseSpec {

  /*
   0.1725 € // Banana
   630 € // House
   31.44 € // Bicycle
   = 661.6125 € per year
   */
  "userTwo with all products" should "receive a good offer" in {
    CoyaProcessor.priceFor(userOne, List(funBike, coolHouse, justRightBanana)) shouldEqual Some(BigDecimal(661.6125))
  }
}
