data class Item(val name: String, val value: Double)

data class Cart(val items: List<Item>) {
  fun total(): Double {
    return items.stream()
            .map({ i -> i.value })
            .reduce(0.0, { a, b -> a + b})
  }
}

interface Discount {
  fun handle(cart: Cart): Double
  fun apply(cart: Cart): Boolean
  fun execute(cart: Cart) : Double
}

abstract class DiscountChain(var next: Discount? = null) : Discount {
  override fun execute(cart: Cart) : Double {
    if (apply(cart)) {
      return handle(cart)
    } else {
      return this.next!!.execute(cart)
    }
  }

}

class MoreThanFiveItemsDiscount: DiscountChain() {
  override fun apply(cart: Cart): Boolean {
    return cart.items.size > 5
  }

  override fun handle(cart: Cart): Double {
    return cart.total() * 0.1
  }

}

class MoreThanFiveHundredOfTotal: DiscountChain() {
  override fun apply(cart: Cart): Boolean {
    return cart.total() > 500.00
  }

  override fun handle(cart: Cart): Double {
    return cart.total() * 0.15
  }
  
}

class ZeroDiscount: DiscountChain() {
  override fun apply(cart: Cart): Boolean {
    return true
  }

  override fun handle(cart: Cart): Double {
    return 0.0
  }

}

fun main(args: Array<String>) {
  val d2 = MoreThanFiveHundredOfTotal()
  d2.next = ZeroDiscount()

  val d1 = MoreThanFiveItemsDiscount()
  d1.next = d2

  val cart: Cart = Cart(listOf(
    Item("PS4", 1500.00),
    Item("Persona 5 PS4", 249.90)
  ))

  println(d1.execute(cart))
}