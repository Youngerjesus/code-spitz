package week_05

interface Validator {
    fun <T: Any> check(v: Any): Result<T>
}
