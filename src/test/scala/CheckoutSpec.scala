package checkout

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {

  "Checkout without offers" should "calculate total for single apple" in {
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

  "Checkout with offers" should "apply buy one get one free on single apple" in {
    Checkout.totalWithOffers(List(Apple)) shouldBe BigDecimal("0.60")
  }

  it should "apply buy one get one free on two apples" in {
    Checkout.totalWithOffers(List(Apple, Apple)) shouldBe BigDecimal("0.60")
  }

  it should "apply buy one get one free on three apples" in {
    Checkout.totalWithOffers(List(Apple, Apple, Apple)) shouldBe BigDecimal("1.20")
  }

  it should "apply buy one get one free on four apples" in {
    Checkout.totalWithOffers(List(Apple, Apple, Apple, Apple)) shouldBe BigDecimal("1.20")
  }

  it should "apply 3 for 2 on two oranges" in {
    Checkout.totalWithOffers(List(Orange, Orange)) shouldBe BigDecimal("0.50")
  }

  it should "apply 3 for 2 on three oranges" in {
    Checkout.totalWithOffers(List(Orange, Orange, Orange)) shouldBe BigDecimal("0.50")
  }

  it should "apply 3 for 2 on four oranges" in {
    Checkout.totalWithOffers(List(Orange, Orange, Orange, Orange)) shouldBe BigDecimal("0.75")
  }

  it should "apply 3 for 2 on six oranges" in {
    Checkout.totalWithOffers(List(Orange, Orange, Orange, Orange, Orange, Orange)) shouldBe BigDecimal("1.00")
  }

  it should "apply both offers on mixed items" in {
    Checkout.totalWithOffers(List(Apple, Apple, Orange, Apple)) shouldBe BigDecimal("1.45")
  }

  it should "apply both offers correctly" in {
    Checkout.totalWithOffers(List(Apple, Apple, Orange, Orange, Orange)) shouldBe BigDecimal("1.10")
  }

  it should "handle empty basket" in {
    Checkout.totalWithOffers(List.empty) shouldBe BigDecimal("0.00")
  }
}