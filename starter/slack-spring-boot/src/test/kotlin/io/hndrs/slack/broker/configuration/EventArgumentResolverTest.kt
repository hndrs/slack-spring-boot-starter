package io.hndrs.slack.broker.configuration

import io.hndrs.slack.api.contract.jackson.event.EventRequest
import io.hndrs.slack.api.contract.jackson.event.SlackEvent
import io.hndrs.slack.api.contract.jackson.event.sample
import io.hndrs.slack.api.contract.jackson.event.type.user.UserChange
import io.hndrs.slack.broker.RequestTestUtils
import io.hndrs.slack.broker.RequestTestUtils.jsonBody
import io.hndrs.slack.broker.RequestTestUtils.mockMethodParameter
import io.hndrs.slack.broker.RequestTestUtils.mockNativeWebRequest
import io.hndrs.slack.broker.event.http.Event
import io.hndrs.slack.broker.event.http.EventArgumentResolver
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

internal class EventArgumentResolverTest {

    @Test
    fun supportsParameter() {
        assertTrue(
            EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(EventRequest::class.java, Event::class.java)))

        assertFalse(
            EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(EventRequest::class.java, RequestTestUtils.TestAnnotation::class.java)))

        assertFalse(
            EventArgumentResolver("")
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
