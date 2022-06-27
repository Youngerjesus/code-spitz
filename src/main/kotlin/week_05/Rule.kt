package week_05

interface Rule {
    fun check(target: RuleResult): RuleResult
}
