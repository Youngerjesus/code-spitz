package week_03

import java.time.LocalDateTime

class Once(private val at: LocalDateTime): Scheduler() {
    private var isSend = false
    override fun isSend(now: LocalDateTime): Boolean {
        if (!isSend && at <= now) {
            isSend = true
            return false
        }
        else return true
    }
}
