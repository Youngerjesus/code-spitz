package week_03

import java.time.LocalDateTime

class User(private var name: String) {
    init {
        Looper.users.add(this)
    }

    private val items = hashSetOf<Item>()

    fun addItem(vararg item: Item) {
        items += item
    }

    fun send(now: LocalDateTime) {
        items.forEach { it.send(now) }
    }
}
