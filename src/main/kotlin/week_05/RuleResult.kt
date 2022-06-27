package week_05

sealed interface RuleResult {
    companion object {
        private val defaultMsg = "invalid"

        fun<T: Any> value(v: T): RuleResult = Value(v)
        fun fail(msg: String): RuleResult = Fail(msg)
    }

    data class Value<T: Any>(val value: T): RuleResult
    data class Fail(val msg: String): RuleResult
}
