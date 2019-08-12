package com.kreait.slack.broker.configuration

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.sample
import com.kreait.slack.broker.RequestTestUtils
import com.kreait.slack.broker.RequestTestUtils.jsonBody
import com.kreait.slack.broker.RequestTestUtils.mockMethodParameter
import com.kreait.slack.broker.RequestTestUtils.mockNativeWebRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

internal class InteractiveResponseArgumentResolverTest {

    @Test
    fun supportsParameter() {
        assertTrue(InteractiveResponseArgumentResolver("")
                .supportsParameter(mockMethodParameter(InteractiveComponentResponse::class.java, InteractiveResponse::class.java)))

        Assertions.assertFalse(InteractiveResponseArgumentResolver("")
                .supportsParameter(mockMethodParameter(InteractiveComponentResponse::class.java, RequestTestUtils.TestAnnotation::class.java)))

        Assertions.assertFalse(InteractiveResponseArgumentResolver("")
                .supportsParameter(mockMethodParameter(Any::class.java, InteractiveResponse::class.java)))
    }

    @Test
    fun internalResolveArgument() {
        //setup
        val interactiveComponentResponse = InteractiveComponentResponse.sample()
        val signingSecret = "mySecret"
        val timestamp = Instant.now()

        val mockNativeWebRequest = mockNativeWebRequest(timestamp, signingSecret, mapOf(Pair("payload", listOf(jsonBody(interactiveComponentResponse)))))

        //test
        val resolvedArgument = InteractiveResponseArgumentResolver(signingSecret)
                .resolveArgument(mockMethodParameter(InteractiveComponentResponse::class.java, InteractiveResponse::class.java), null, mockNativeWebRequest, null)

        Assertions.assertEquals(interactiveComponentResponse, resolvedArgument)
    }

}
