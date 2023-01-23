package week_06.continuationTask

class ContinuationTask(
    private val dispatcher: Dispatcher,
    isLazy: Boolean,
    block: (Continuation) -> Unit
) : Runnable {
    private val task = Task(block) // TODO 하나의 블락만 받으면 된다. step 별로 subRoutine 이 쪼개질 것이니.

    init {
        if (!isLazy) launch()
    }

    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(6)
            if (task.isCompleted == State.MARK) break
            if (task.isStarted == State.READY) {
                task.isStarted = State.MARK
                // TODO task 안에 실행될 block (=task) 가 있다. 그리고 continuation 안에 어떤 블락이 실행되야하는 정보가 있다.
                // TODO 처리하다가 다른 스레드가 처리해야되는거면 또 task 와 continuation (task 안에 continuation 이 있음) 을 다른 스레드에서 실행하도록 하면 된다.
                task.run(task.continuation)
            }
        }

        task.continuation.failed?.let { throw it}
    }

    fun launch() {
        dispatcher.start(this)
    }
}
