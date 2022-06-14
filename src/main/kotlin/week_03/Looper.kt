package week_03

abstract class Looper {
    companion object {
        val users = hashSetOf<User>()
    }

    var isRunning = false
        private set

    fun start() {
        isRunning = true
        started()
    }

    fun end() {
        isRunning = false
        ended()
    }

    protected abstract fun started()

    protected abstract fun ended()
}
