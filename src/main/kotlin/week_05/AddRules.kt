package week_05

class AddRules(blocK: AddRules.() -> Unit) : MutableSet<Rule> by mutableSetOf() {
    init { blocK(this) }
}
