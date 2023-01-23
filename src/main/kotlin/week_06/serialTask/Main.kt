package week_06.serialTask

fun main() {
    val dispatcher = Dispatcher.FixedDispatcher(10)

    for (i in 0..5) {
        val looper = SerialTask(dispatcher,
            {
                println("$i-0 ${Thread.currentThread().id}")
                it.resume()
            },
            {
                println("$i-1 ${Thread.currentThread().id}")
                it.resume()
            },
            {
                println("$i-2 ${Thread.currentThread().id}")
                it.resume()
            }
        )

        // TODO 하나의 스레드가 책임지고 서브 루틴을 가지고 있는 테스크를 처리한다. 하나의 스레드가 여러개의 테스크를 처리할 수도 있지.
        // TODO 근데 테스크 처리가 순차적이다. A 테스크가 끝나야 B 테스크를 시작하는게 가능함.
        looper.launch()
    }

    dispatcher.join()
}
