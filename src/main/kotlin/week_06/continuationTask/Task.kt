package week_06.continuationTask

class Task internal constructor(internal val run: (Continuation) -> Unit) {
    internal val continuation = Continuation(this)
    internal var isStarted = State.READY
    internal var isCompleted = State.READY
    internal var env: MutableMap<String, Any?> = hashMapOf()
}
