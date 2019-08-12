package com.kreait.slack.api.spring

import org.junit.jupiter.api.Assertions

class Verifier(private val expected: Any?) {

    var actual: Any? = null

    fun set(actual: Any?) {
        this.actual = actual
    }

    fun verify() {
        Assertions.assertEquals(expected, actual)
    }
}
