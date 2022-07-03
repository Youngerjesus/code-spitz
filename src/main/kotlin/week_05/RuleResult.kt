package week_05

sealed interface RuleResult {
    companion object {
        private const val defaultMessage = "invalid"
        fun <T: Any> value(v: T): RuleResult = Value(v)
        fun fail(msg: String?): RuleResult = Fail(msg ?: defaultMessage)
    }

    data class Value<T: Any>(val value: T): RuleResult
    data class Fail(val msg: String): RuleResult
}
