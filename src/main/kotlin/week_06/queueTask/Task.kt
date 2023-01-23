package week_06.queueTask

class Task(val run: (Controller) -> Unit) {
    var isCompleted = false
    var result: Result<Any?>? = null
    var next: Task? = null
}

class Controller internal constructor(private val task: Task){
    val data get() = task.result

    fun cancel(throwable: Throwable) {
        task.next?.result = Result.failure(throwable)
        task.isCompleted = true
    }

    fun resume(data: Any? = null) { // TODO next 에 결과를 전파하고, 현재 테스크는 완료로 취급한다.
        task.next?.result = Result.success(data)
        task.isCompleted = true
    }
}
