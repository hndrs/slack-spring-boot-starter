package com.kreait.slack.broker.configuration

import com.kreait.slack.api.contract.jackson.event.EventRequest
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.api.contract.jackson.event.sample
import com.kreait.slack.api.contract.jackson.event.type.user.UserChange
import com.kreait.slack.broker.RequestTestUtils
import com.kreait.slack.broker.RequestTestUtils.jsonBody
import com.kreait.slack.broker.RequestTestUtils.mockMethodParameter
import com.kreait.slack.broker.RequestTestUtils.mockNativeWebRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

internal class EventArgumentResolverTest {

    @Test
    fun supportsParameter() {
        assertTrue(EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(EventRequest::class.java, Event::class.java)))

        assertFalse(EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(EventRequest::class.java, RequestTestUtils.TestAnnotation::class.java)))

        assertFalse(EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(Any::class.java, Event::class.java)))
    }

    @Test
    fun internalResolveArgument() {

        //setup
        val slackEvent = SlackEvent.sample(UserChange.sample())
        val signingSecret = "mySecret"
        val timestamp = Instant.now()

        val mockNativeWebRequest = mockNativeWebRequest(timestamp, signingSecret, jsonBody(slackEvent))

        //test
        val resolvedArgument = EventArgumentResolver(signingSecret)
                .resolveArgument(mockMethodParameter(EventRequest::class.java, Event::class.java), null, mockNativeWebRequest, null)

        Assertions.assertEquals(slackEvent, resolvedArgument)
    }
}
