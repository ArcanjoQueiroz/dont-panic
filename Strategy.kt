data class Budget(val value: Double);

val ICMS = { budget: Budget -> budget.value * 0.6 }

val ISS = { budget: Budget -> budget.value * 0.8 }

class BudgetCalculator {
  fun calculate(budget: Budget, tax: (Budget) -> Double): Double {
    return tax.invoke(budget)
  }
}

// Strategy Design Pattern
fun main(args: Array<String>) {
  val budgetCalculator = BudgetCalculator()

  val budget = Budget(500.00)

  println(budgetCalculator.calculate(budget, ISS))
  println(budgetCalculator.calculate(budget, ICMS))
}