package week_06.continuationTask

fun main() {
    val dispatcher = Dispatcher.FixedDispatcher(10)

    for (i in 0..5) {
        // TODO 현재 step 에 따라서 어떤 subRoutine 이 실행되야하는지 정의되어있다.
        // TODO resume() 을 통해서 하나의 subRoutine 이 실행되면 다음 subRoutine 이 어떤 것이 실행되야 하는지 상태를 변경해준다. 그리고 task 의 상태도 다시 재개할 수 있는 상태로 변경해준다.
        // TODO 그러면 스레드가 테스크를 다시 시작하게 된다. 이렇게 다음 서브 루틴이 시작된다. 시작할 때 Continuation 을 게속 패싱하면서 시작한다. 여기에 어떤 서브루틴이 실행되야하는지 정해져있고.
        // TODO suspend function 을 쓰면 step 이 정해져서 컴파일 되겠지,

        // TODO 나중에는 하나의 스레드가 여러개의 테스크를 처리할 수 있도록 해야지 네트워크 통신이 있다면 다른 스레드가 테스크를 처리하도록 Continuation 객체를 던지고 자신은 while 을 돌면서 다른 테스크들을 또 처리하도록 되야지
        ContinuationTask(dispatcher, false) {
            when (it.step) {
                0 -> {
                    println("$i-0 ${Thread.currentThread().id}")
                    it.resume(1)
                }

                1 -> {
                    println("$i-1 ${Thread.currentThread().id}")
                    it.resume(2)
                }

                2 -> {
                    println("$i-2 ${Thread.currentThread().id}")
                    it.complete()
                }
            }
        }
    }
}
