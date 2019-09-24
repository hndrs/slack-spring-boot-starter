package com.kreait.slack.api.group

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ApiCallResultTest {

    @Test
    fun wasSuccess() {
        assertTrue(ApiCallResult<String, String>(success = "").wasSuccess())
        assertFalse(ApiCallResult<String, String>(success = "").wasFailure())
    }

    @Test
    fun wasFailure() {
        assertTrue(ApiCallResult<String, String>(failure = "").wasFailure())
        assertFalse(ApiCallResult<String, String>(failure = "").wasSuccess())
    }
}
