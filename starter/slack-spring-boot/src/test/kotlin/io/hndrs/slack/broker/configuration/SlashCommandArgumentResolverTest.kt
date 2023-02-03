package io.hndrs.slack.broker.configuration

import io.hndrs.slack.broker.RequestTestUtils
import io.hndrs.slack.broker.RequestTestUtils.mockMethodParameter
import io.hndrs.slack.broker.RequestTestUtils.mockNativeWebRequest
import io.hndrs.slack.broker.command.Command
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.command.SlashCommandArgumentResolver
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.toParameterMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant

internal class SlashCommandArgumentResolverTest {

    @Test
    fun supportsParameter() {
        Assertions.assertTrue(
            SlashCommandArgumentResolver("")
                .supportsParameter(mockMethodParameter(SlashCommand::class.java, Command::class.java))
        )

        Assertions.assertFalse(
            SlashCommandArgumentResolver("")
                .supportsParameter(
                    mockMethodParameter(SlashCommand::class.java, RequestTestUtils.TestAnnotation::class.java)
                )
        )

        Assertions.assertFalse(
            SlashCommandArgumentResolver("")
                .supportsParameter(mockMethodParameter(Any::class.java, Command::class.java))
        )
    }

    @Test
    fun internalResolveArgument() {
        // setup
        val command = SlashCommand.sample()
        val signingSecret = "mySecret"
        val timestamp = Instant.now()

        val mockNativeWebRequest = mockNativeWebRequest(timestamp, signingSecret, command.toParameterMap())

        // test
        val resolvedArgument = SlashCommandArgumentResolver(signingSecret)
            .resolveArgument(
                mockMethodParameter(SlashCommand::class.java, Command::class.java),
                null,
                mockNativeWebRequest,
                null
            )

        Assertions.assertEquals(command, resolvedArgument)
    }
}
