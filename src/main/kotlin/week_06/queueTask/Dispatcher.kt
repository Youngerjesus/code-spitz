package week_06.queueTask

import java.util.concurrent.Executors

interface Dispatcher {
    fun start(looper: EventLooper)
    fun join()

    class FixedDispatcher(private val threads: Int): Dispatcher {
        private val executor = Executors.newFixedThreadPool(threads)

        override fun start(looper: EventLooper) {
            for (i in 1..threads) executor.execute(looper) // TODO Executor 의 모든 스레드가 다 looper 를 실행한다.
        }

        override fun join() {
            while (!executor.isShutdown) {} // TODO Executor 가 끝날 때까지 대기한다.
        }
    }
}
