package week_03

import java.time.Clock
import java.time.LocalDateTime

object ThreadLooper : Looper() {
    private val thread by lazy {
        Thread {
            while (isRunning && !Thread.currentThread().isInterrupted) {
                val now = LocalDateTime.now()
                users.forEach {it.send(now)}
                Thread.sleep(1000)
            }
        }
    }

    override fun started() {
        if(!thread.isAlive) thread.start()
    }

    override fun ended() {

    }
}
