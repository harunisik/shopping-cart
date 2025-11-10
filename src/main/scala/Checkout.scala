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

  // Alternative implementation accepting strings
  def totalFromStrings(items: List[String]): BigDecimal = {
    val parsed = items.map {
      case "Apple" => Apple
      case "Orange" => Orange
      case other => throw new IllegalArgumentException(s"Unknown item: $other")
    }
    total(parsed)
  }
}