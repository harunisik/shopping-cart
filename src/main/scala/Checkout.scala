package checkout

sealed trait Item {
  def price: BigDecimal
}

case object Apple extends Item {
  override def price: BigDecimal = BigDecimal("0.60")
}

case object Orange extends Item {
  override def price: BigDecimal = BigDecimal("0.25")
}

object Checkout {
  def total(items: List[Item]): BigDecimal = {
    items.map(_.price).sum
  }

  def totalWithOffers(items: List[Item]): BigDecimal = {
    val appleCount = items.count(_ == Apple)
    val orangeCount = items.count(_ == Orange)

    // Buy one get one free on apples: pay for ceil(count/2)
    val applesCost = (appleCount / 2 + appleCount % 2) * Apple.price

    // 3 for the price of 2 on oranges: pay for (count - count/3)
    val orangesCost = (orangeCount - orangeCount / 3) * Orange.price

    applesCost + orangesCost
  }

  def totalFromStrings(items: List[String]): BigDecimal = {
    val parsed = items.map {
      case "Apple" => Apple
      case "Orange" => Orange
      case other => throw new IllegalArgumentException(s"Unknown item: $other")
    }
    total(parsed)
  }

  def totalWithOffersFromStrings(items: List[String]): BigDecimal = {
    val parsed = items.map {
      case "Apple" => Apple
      case "Orange" => Orange
      case other => throw new IllegalArgumentException(s"Unknown item: $other")
    }
    totalWithOffers(parsed)
  }
}