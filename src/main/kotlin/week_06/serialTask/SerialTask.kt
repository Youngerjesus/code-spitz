package week_06.serialTask

class SerialTask(private val dispatcher: Dispatcher, vararg blocks: (Controller) -> Unit) : Runnable {
    private var task: Task

    init {
        if (blocks.isEmpty()) throw Throwable("no blocks")
        var prev = Task(blocks[0])
        task = prev
        prev.isStarted = State.MARK

        for (i in 1..blocks.lastIndex) {
            val task = Task(blocks[i])
            prev.next = task
            prev = task
        }
    }
    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(5)
            if (task.isCompleted == State.MARK) {
                task.next?.let {
                    it.isStarted = State.MARK
                    task = it
                }
            }
            if (task.isStarted == State.MARK) {
                task.run(Controller(task))
                task.isStarted = State.CONFIRM
            }
        }
    }

    fun launch() {
        dispatcher.start(this)
    }
}
