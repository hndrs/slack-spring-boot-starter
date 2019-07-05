package io.olaph.slack.broker.exception

class ExceptionChain constructor(private val listOFErrors: MutableList<Throwable> = mutableListOf()) {

    fun add(t: Throwable) {
        this.listOFErrors.add(t)
    }

    fun trigger() {
        listOFErrors.firstOrNull { it is MustThrow }?.let { throw it }
    }
}