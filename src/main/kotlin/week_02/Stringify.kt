package week_02

import kotlin.reflect.KProperty

fun stringify(value: Any): String {
    return StringBuilder().run {
        jsonValue(value, this)
        toString()
    }
}

private fun jsonValue(value: Any?, builder: StringBuilder) = when (value) {
    null -> builder.append("null")
    is String -> builder.append(jsonString(value))
    is Boolean, is Number -> builder.append("$value")
    is List<*> -> builder.append(jsonList(value, builder))
    else -> builder.append(jsonObject(value, builder))
}

fun jsonList(target: List<*>, builder: StringBuilder): String {
    builder.append("[")
    target.joinTo( {builder.append(",")}) {
        jsonValue(it, builder)
    }
    builder.append("]")
    return "$builder"
}

fun <T: Any> jsonObject(target: T, builder: StringBuilder): String {
    builder.append("{")
    target::class.members.filterIsInstance<KProperty<*>>()
        .joinTo({builder.append(",")}) {
            jsonValue(it.name, builder)
            builder.append(":")
            jsonValue(it.getter.call(target), builder)
        }
    builder.append("}")
    return "$builder"
}


fun jsonString(v: String) = v.replace("\"", "\\\"")

class Json0(val a: Int, val b: String)

class Json1(val a: Int, val b: String, val c: List<String>)

fun main() {

}

fun <T> Iterable<T>.joinTo(sep: ()-> Unit, transform:(T) -> Unit) {
    forEachIndexed() { count, element ->
        if (count != 0) sep()
        transform(element)
    }
}
