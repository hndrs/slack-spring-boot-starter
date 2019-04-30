package io.olaph.slack.broker.configuration

import io.olaph.slack.broker.configuration.ArgumentResolverTestUtil.mockMethodParameter
import io.olaph.slack.broker.configuration.ArgumentResolverTestUtil.mockNativeWebRequest
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.sample
import io.olaph.slack.dto.jackson.toParameterMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant

internal class SlackCommandArgumentResolverTest {

    @Test
    fun supportsParameter() {
        Assertions.assertTrue(SlackCommandArgumentResolver("")
                .supportsParameter(mockMethodParameter(SlackCommand::class.java, Command::class.java)))

        Assertions.assertFalse(SlackCommandArgumentResolver("")
                .supportsParameter(mockMethodParameter(SlackCommand::class.java, ArgumentResolverTestUtil.TestAnnotation::class.java)))

        Assertions.assertFalse(SlackCommandArgumentResolver("")
                .supportsParameter(mockMethodParameter(Any::class.java, Command::class.java)))
    }

    @Test
    fun internalResolveArgument() {
        //setup
        val command = SlackCommand.sample()
        val signingSecret = "mySecret"
        val timestamp = Instant.now()

        val mockNativeWebRequest = mockNativeWebRequest(timestamp, signingSecret, "", command.toParameterMap())

        //test
        val resolvedArgument = SlackCommandArgumentResolver(signingSecret)
                .resolveArgument(mockMethodParameter(SlackCommand::class.java, Command::class.java), null, mockNativeWebRequest, null)

        Assertions.assertEquals(command, resolvedArgument)
    }

}
