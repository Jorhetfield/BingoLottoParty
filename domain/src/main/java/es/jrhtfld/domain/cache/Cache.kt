package es.jrhtfld.domain.cache

abstract class Cache<T>(private var validityTime: Long = VALIDITY_LONG_TIME) {

    companion object {
        const val VALIDITY_LONG_TIME = 1200000L
    }

    protected var data: T? = null
    private var timestamp: Long = 0

    private fun isValidTime(): Boolean {
        return System.currentTimeMillis() - timestamp < validityTime
    }

    fun load(): T? {
        return if (data != null && isValidTime()) data
        else {
            clear()
            null
        }
    }

    fun save(value: T) {
        data = value
        updateTimestamp()
    }

    fun clear() {
        data = null
    }

    private fun updateTimestamp() {
        timestamp = System.currentTimeMillis()
    }
}