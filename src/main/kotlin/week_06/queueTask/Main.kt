package week_06.queueTask

fun main() {
    val looper = EventLooper(Dispatcher.FixedDispatcher(10))

    for (i in 0..5) { // TODO Task 5개를 넣고, 하나의 Task 를 쪼개서 3개로 넣었다. 0,1,2,3,4,5 순서대로 테스크가 들어간다.
        looper.linkedTask(
            {
                println("$i-0 ${Thread.currentThread().id}")
                Thread.sleep(i * 100L)
                it.resume()
            },
            {
                println("$i-1 ${Thread.currentThread().id}")
                Thread.sleep(i * 100L)
                it.resume()
            },
            {
                println("$i-2 ${Thread.currentThread().id}")
                Thread.sleep(i * 100L)
                it.resume()
            }
        )
    }

    // TODO 0-0번 테스크부터 꺼내서 실행한다. A 스레드가 0-0 을 실행했다고 가정해보자. Thread.sleep() 으로 차단당한뒤, 다시 resume 한다. 그러면 완료가된다.
    // TODO 0-0 테스크가 끝난다면 B 스레드가 들어와서 0-1 테스크를 큐에다가 추가한다.
    // TODO 그 다음 EventLooper 로 들어온 C 스레드가 1-0 번 테스트틀 꺼내서 실행한다.
    // TODO 여기서의 문제점은 스레드가 놀고있다. 하나의 테스크를 처리되는 동안을 기다리고 있다. currentTask slot 이 하나라는 문제. 멀티 스레드를 사용할 수 없게 되는 문제. 쌓아 놓는 순서를 어길 수 없는 문제 (원래는 처리가 된 순서로 가야 효율적).
    looper.launch()
    looper.join()
}
