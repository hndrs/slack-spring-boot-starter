package io.olaph.slack.broker.exception

/**
 * Collects errors and throws the first of them that implements [MustThrow]. This is useful when the execution of multiple
 * functions has to be guaranteed and thus exceptions are catched but you might have an exception that has to be thrown.
 */
class ExceptionChain constructor(private val listOFErrors: MutableList<Throwable> = mutableListOf()) {

    /**
     * Add an Exception to the chain
     */
    fun add(t: Throwable) {
        this.listOFErrors.add(t)
    }

    /**
     * Throws first exception in the chain that implements the [MustThrow] interface
     */
    fun evaluate() {
        listOFErrors.firstOrNull { it is MustThrow }?.let { throw it }
    }
}
