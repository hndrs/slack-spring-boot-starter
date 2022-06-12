package io.hndrs.slack.broker.configuration

import io.hndrs.slack.api.contract.jackson.InteractiveComponentResponse
import io.hndrs.slack.api.contract.jackson.InteractiveMessage
import io.hndrs.slack.api.contract.jackson.sample
import io.hndrs.slack.broker.RequestTestUtils
import io.hndrs.slack.broker.RequestTestUtils.jsonBody
import io.hndrs.slack.broker.RequestTestUtils.mockMethodParameter
import io.hndrs.slack.broker.RequestTestUtils.mockNativeWebRequest
import io.hndrs.slack.broker.interactive.InteractiveResponse
import io.hndrs.slack.broker.interactive.InteractiveResponseArgumentResolver
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

internal class InteractiveResponseArgumentResolverTest {

    @Test
    fun supportsParameter() {
        assertTrue(
            InteractiveResponseArgumentResolver("")
                .supportsParameter(mockMethodParameter(InteractiveComponentResponse::class.java, InteractiveResponse::class.java)))

        Assertions.assertFalse(
            InteractiveResponseArgumentResolver("")
                .supportsParameter(mockMethodParameter(InteractiveComponentResponse::class.java, RequestTestUtils.TestAnnotation::class.java)))

        Assertions.assertFalse(
            InteractiveResponseArgumentResolver("")
                .supportsParameter(mockMethodParameter(Any::class.java, InteractiveResponse::class.java)))
    }

    @Test
    fun internalResolveArgument() {
        //setup
        val interactiveComponentResponse = InteractiveMessage.sample()
        val signingSecret = "mySecret"
        val timestamp = Instant.now()

        val mockNativeWebRequest = mockNativeWebRequest(timestamp, signingSecret, mapOf(Pair("payload", listOf(jsonBody(interactiveComponentResponse)))))

        //test
        val resolvedArgument = InteractiveResponseArgumentResolver(signingSecret)
                .resolveArgument(mockMethodParameter(InteractiveComponentResponse::class.java, InteractiveResponse::class.java), null, mockNativeWebRequest, null)

        Assertions.assertEquals(interactiveComponentResponse, resolvedArgument)
    }

}
