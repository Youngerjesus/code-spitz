package week_03

import java.time.LocalDateTime

class Item(val title: String, val content: String) {
    private val schedulers = hashSetOf<Scheduler>()

    fun addScheduler(vararg scheduler: Scheduler) {
        schedulers += scheduler
    }

    fun send(now: LocalDateTime) {
        schedulers.forEach { it.send(this, now)}
    }

}
