package io.hndrs.slack.api.contract.jackson.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Instant Extension Tests")
class InstantExtensionsTests {

    @Test
    @DisplayName("Bidirectional Conversion to/from SlackTimestamp with mircos")
    fun toSlackTimestampStringWithMicros() {

        val slackTimestampSample = "1361482916.000004"

        val toSlackTimeStampInstant = "1361482916.000004".toSlackTimeStampInstant()

        val toSlackTimestampString = toSlackTimeStampInstant.toSlackTimestampString()

        Assertions.assertEquals(slackTimestampSample, toSlackTimestampString)
    }

    @Test
    @DisplayName("Bidirectional Conversion to/from SlackTimestamp without micros")
    fun toSlackTimestampConversionNoMicros() {

        val slackTimestampSample = "1361482916"

        val toSlackTimeStampInstant = "1361482916".toSlackTimeStampInstant()

        val toSlackTimestampString = toSlackTimeStampInstant.toSlackTimestampString()

        Assertions.assertEquals(slackTimestampSample, toSlackTimestampString)
    }
}
