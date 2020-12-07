package com.kreait.slack.broker.autoconfiguration.credentials

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.BeanInstantiationException

internal class CredentialsFailureAnalyzerTest {


    @Test
    @DisplayName("Application Credentials Exceptions")
    fun testFailureAnalyzerWhenCauseApplicationCredentialsException() {
        val beanInstantiationException =
            BeanInstantiationException(String::class.java, "Message", ApplicationCredentialsException("Any Reason"))

        val analyze = CredentialsFailureAnalyzer().analyze(beanInstantiationException)
        Assertions.assertNotNull(analyze)
    }

    @Test
    @DisplayName("Other Exceptions")
    fun testFailureAnalyzerWhenCauseNotApplicationCredentialsException() {
        val beanInstantiationException =
            BeanInstantiationException(String::class.java, "Message", IllegalStateException())

        val analyze = CredentialsFailureAnalyzer().analyze(beanInstantiationException)
        Assertions.assertNull(analyze)
    }
}
