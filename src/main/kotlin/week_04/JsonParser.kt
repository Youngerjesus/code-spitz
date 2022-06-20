package week_04

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KType

// TODO (1) 문자열을 처음에서 끝까지 읽어내며 값을 얻어내는 스캐너가 필요하다. 이를 언어적으론느 어휘 분석기 (lexical analysis, Lexer) 라고 부른다.
// TODO (2) Lexer 와 협력해서 원하는 토큰을 문법에 맞게 추출해 구조체를 생성해주는 걸 Parser 라고 한다.
class JsonParser {
}

class JsonLexer(val json: String) {
    val last = json.lastIndex
    var cursor = 0
        private set
    val curr get() = json[cursor]

    fun next() {
        if (cursor < last) ++cursor
    }

    fun skipWhite() {
        while ("\t\n\r".indexOf(curr) != -1 && cursor < last) next()
    }

    fun isOpenObject(): Boolean = '{' == this.curr
    fun isCloseObject(): Boolean {
        return '}' == this.curr
    }

    fun key(): Nothing? {
        TODO("Not yet implemented")
    }

    fun isComma(): Boolean {
        TODO("Not yet implemented")
    }

    fun number(): String? {
        val start = cursor
        while ("-.0123456789".indexOf(curr) != -1) next()
        return if (start == cursor) null else json.substring(start, cursor)
    }

    fun string(): Any? {
        TODO("Not yet implemented")
    }

    fun int(): Any? {
        TODO("Not yet implemented")
    }

    fun long(): Any? {
        TODO("Not yet implemented")
    }

    fun float(): Any? {
        TODO("Not yet implemented")
    }

    fun double(): Any? {
        TODO("Not yet implemented")
    }
}

fun <T:Any> parseJson(target: T, json: String): T? {
    val lexer = JsonLexer(json)
    lexer.skipWhite()
    return parseObject(lexer, target)
}

fun <T> parseObject(lexer: JsonLexer, target: T): T? {
    if (!lexer.isOpenObject()) return null
    lexer.next()

    val props = target!!::class.members
        .filterIsInstance<KMutableProperty<*>>()
        .associate {
            it.name to it
        }

    while (!lexer.isCloseObject()) {
        lexer.skipWhite()
        val key = lexer.key() ?: return null
        val prop = props[key] ?: return null
        val value = jsonValue(lexer, prop.returnType) ?: return null
        prop.setter.call(target, value)
        lexer.skipWhite()
        if(lexer.isComma()) lexer.next()
    }

    return target
}

fun jsonValue(lexer: JsonLexer, type: KType): Any? {
    return when (val cls = type.classifier as? KClass<*> ?: return null) {
        String::class -> lexer.string()
        Int::class -> lexer.int()
        Long::class -> lexer.long()
        Float::class -> lexer.float()
        Double::class -> lexer.double()
        else -> {}
    }
}

