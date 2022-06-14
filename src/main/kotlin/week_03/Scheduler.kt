package week_03

import java.time.LocalDateTime

abstract class Scheduler {
    private val senders = hashSetOf<Sender>()

    fun addSender(vararg sender: Sender) {
        senders += sender
    }

    fun send(item: Item, now: LocalDateTime) {
        if(isSend(now)) senders.forEach { it.send(item) }
    }

    protected abstract fun isSend(now: LocalDateTime): Boolean
}
