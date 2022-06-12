package week_01

class Calc {
    val trim = """"[^\d\w\W]""".toRegex()
    val groupMD = """\(?[\+\-])?([\*\/])|()""".toRegex()

    fun trim(v: String): String {
        return v.replace(trim, "")
    }

    fun repMtoPM(v: String) = v.replace("-", "+-")

    fun foldGroup(v: String): Double =
        groupMD.findAll(v)
            .fold(0.0) {acc, cum ->
                val (left, op, right) = cum.groupValues
                val leftValue = left.replace("+", "").toDouble()
                val rightValue = right.replace("+", "").toDouble()
                val result = when(op) {
                    "*" -> leftValue * rightValue
                    "/" -> leftValue / rightValue
                    else -> throw Throwable("error")
                }
                acc + result
            }

    fun calc(v: String) = foldGroup(repMtoPM(trim(v)))
}
