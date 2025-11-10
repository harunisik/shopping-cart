package checkout

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {

  "Checkout" should "calculate total for single apple" in {
    Checkout.total(List(Apple)) shouldBe BigDecimal("0.60")
  }

  it should "calculate total for single orange" in {
    Checkout.total(List(Orange)) shouldBe BigDecimal("0.25")
  }

  it should "calculate total for multiple items" in {
    Checkout.total(List(Apple, Apple, Orange, Apple)) shouldBe BigDecimal("2.05")
  }

  it should "return zero for empty basket" in {
    Checkout.total(List.empty) shouldBe BigDecimal("0.00")
  }

  it should "calculate total from string input" in {
    Checkout.totalFromStrings(List("Apple", "Apple", "Orange", "Apple")) shouldBe BigDecimal("2.05")
  }

  it should "handle mixed items correctly" in {
    Checkout.total(List(Orange, Orange, Apple)) shouldBe BigDecimal("1.10")
  }
}