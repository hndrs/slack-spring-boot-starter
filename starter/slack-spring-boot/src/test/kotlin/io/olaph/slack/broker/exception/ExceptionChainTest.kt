package io.olaph.slack.broker.exception

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ExceptionChainTest {

    private class ThrowableMustThrow : MustThrow()

    @Test
    fun withoutMustThrow() {
        val exceptionChain = ExceptionChain()
        val t1 = Throwable()

        exceptionChain.add(t1)
        assertDoesNotThrow { exceptionChain.evaluate() }
    }

    @Test
    fun withMustThrow() {
        val exceptionChain = ExceptionChain()
        val t1 = Throwable()
        val t2 = ThrowableMustThrow()

        exceptionChain.add(t1)
        exceptionChain.add(t2)

        assertThrows(ThrowableMustThrow::class.java) { exceptionChain.evaluate() }
    }
}
