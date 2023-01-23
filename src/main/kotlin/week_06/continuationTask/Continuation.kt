package week_06.continuationTask

class Continuation internal constructor(private val task: Task) {
    var step = 0
        private set

    // TODO Continuation 객체의 map 에서 필요한 데이터를 가지고온다.
    operator fun get(key: String): Any? = task.env[key]
    operator fun set(key: String, value: Any?) { task.env[key] = value }

    internal var failed: Throwable? = null

    fun cancel(throwable: Throwable) {
        failed = Throwable("step: $step env: ${task.env}", throwable)
        task.isCompleted = State.MARK
    }

    fun complete() {
        task.isCompleted = State.MARK
    }

    fun resume(step: Int) {
        this.step = step
        task.isStarted = State.READY
    }
}
