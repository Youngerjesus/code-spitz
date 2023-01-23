package week_06.queueTask

import java.util.LinkedList
import java.util.Queue

class EventLooper (private val dispatcher: Dispatcher): Runnable {
    private val tasks: Queue<Task> = LinkedList()
    private var currTask: Task? = null

    fun linkedTask(vararg blocks: (Controller) -> Unit) {
        if (blocks.isEmpty()) return

        synchronized(tasks) {
            var prev = Task(blocks[0])
            tasks.add(prev)
            for (i in 1..blocks.lastIndex) {
                val task = Task(blocks[i])
                prev.next = task
                prev = task
            }
        }
    }

    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(16)
            synchronized(this) {
                if (currTask != null) {
                    currTask?.let { curr ->
                        if (curr.isCompleted) { // TODO 완료가 되었으면 새로운 태스크를 실행하도록 함.
                            curr.next?.let {
                                tasks.add(it)
                                currTask = null
                            }
                        }
                    }
                } else {
                    tasks.poll()?.let {// TODO Task 를 꺼내서 실행.
                        currTask = it
                        it.run(Controller(it)) // TODO block 을 받았으니 실제로 해당 block 을 실행하는 함수구나. 그래서 Controller 가 필요한거고
                    }
                }
            }
        }
    }

    fun launch() {
        dispatcher.start(this)
    }

    fun join() {
        dispatcher.join()
    }
}
