package week_05

class RuleDSL(block: RuleDSL.() -> Unit) {
    private val cases = mutableSetOf<AddRules>()

    fun Case(block: AddRules.()-> Unit) {
        cases += AddRules(block)
    }

    init {
        block()
    }
}
