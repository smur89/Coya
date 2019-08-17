package coya.quotes

class BananaProcessorSpec extends BaseSpec {

/*
 0.50 * // Product Value
 1.15 // Base Premium Multiplier
 = 0.575 € per year
 */
  "userOne with justRightBanana" should "receive no offer" in {
    CoyaProcessor.priceFor(userOne, List(justRightBanana)) shouldEqual Some(BigDecimal(0.575))
  }

  "userOne with cleanBanana" should "receive no offer" in {
    CoyaProcessor.priceFor(userOne, List(cleanBanana)) shouldEqual None
  }

  "userOne with rottenBanana" should "receive no offer" in {
    CoyaProcessor.priceFor(userOne, List(rottenBanana)) shouldEqual None
  }

  "highRiskUser with justRightBanana" should "receive no offer" in {
    CoyaProcessor.priceFor(highRiskUser, List(justRightBanana)) shouldEqual None
  }
}
