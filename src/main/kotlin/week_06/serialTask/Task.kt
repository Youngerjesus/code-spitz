package week_06.serialTask

class Task(val run: (Controller) -> Unit) {
    var isStarted = State.READY
    var isCompleted = State.READY
    var result: Result<Any?>? = null
    var next: Task? = null
}

class Controller internal constructor(private val task: Task){
    val data get() = task.result

    fun cancel(throwable: Throwable) {
        task.next?.result = Result.failure(throwable)
        task.isCompleted = State.MARK
    }

    fun resume(data: Any? = null) {
        task.next?.result = Result.success(data)
        task.isCompleted = State.MARK
    }
}

