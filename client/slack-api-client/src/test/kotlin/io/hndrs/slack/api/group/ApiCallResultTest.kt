package io.hndrs.slack.api.group

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ApiCallResultTest {

    @Test
    fun wasSuccess() {
        assertTrue(io.hndrs.slack.api.group.ApiCallResult<String, String>(success = "").wasSuccess())
        assertFalse(io.hndrs.slack.api.group.ApiCallResult<String, String>(success = "").wasFailure())
    }

    @Test
    fun wasFailure() {
        assertTrue(io.hndrs.slack.api.group.ApiCallResult<String, String>(failure = "").wasFailure())
        assertFalse(io.hndrs.slack.api.group.ApiCallResult<String, String>(failure = "").wasSuccess())
    }
}
