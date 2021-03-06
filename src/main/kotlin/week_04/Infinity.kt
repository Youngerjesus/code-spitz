package week_04

import kotlin.reflect.KProperty

data class Infinity<T>(
    private val value: T,
    private val limit: Int = -1,
    private val nextValue: (T) -> T
) {

    class Iter<T> (private var item: Infinity<T>): Iterator<T> {
        override fun hasNext(): Boolean {
            return item.limit != 0
        }

        override fun next(): T {
            val result by item
            item = item.next()
            return result
        }
    }

    operator fun iterator() = Iter(this)
    operator fun getValue(ref: Any?, prop: KProperty<*>) = value

    fun next() = Infinity(nextValue(value), limit - 1, nextValue)
}

fun main() {
    val a = Infinity(0) { it + 1}
    val limit = 20

}
