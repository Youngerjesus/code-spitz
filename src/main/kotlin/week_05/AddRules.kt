package week_05

class AddRules(block: AddRules.() -> Unit): MutableSet<Rule> by mutableSetOf() {
    init {
        block(this)
    }

    inner class equals(private val base: Any, private val msg: String?): Rule {
        override fun check(target: RuleResult): RuleResult {
            return if (target is RuleResult.Value<*> && base == target.value) target
            else RuleResult.fail(msg)
        }

        init {
            this@AddRules += this // set 이기에 가능한.
        }
    }
}

fun AddRules.length(length: Int, msg: String?) {
    this += Length(length, msg)
}

fun main() {
    AddRules {
        equals(3, "not 3")

        length(5, "not length 5")
    }
}
