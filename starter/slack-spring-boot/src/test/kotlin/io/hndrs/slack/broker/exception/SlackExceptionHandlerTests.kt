package io.hndrs.slack.broker.exception

import io.hndrs.slack.broker.RequestTestUtils
import io.hndrs.slack.broker.command.CommandBroker
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.command.SlashCommandArgumentResolver
import io.hndrs.slack.broker.command.SlashCommandReceiver
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.security.VerificationException
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.hndrs.slack.broker.toParameterMap
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import java.time.Instant

/**
 * handles slack-specific exceptions
 *
 */
@DisplayName("SlackExceptionHandler")
internal class SlackExceptionHandlerTests {

    @Nested
    @DisplayName("Unit")
    internal class Unit {

        @Test
        @DisplayName("IllegalArgumentException without JsonMapping Exception")
        fun illegalArgumentExceptionWithoutJson() {
            val response = SlackExceptionHandler()
                .handleIllegalArgumentException(
                    IllegalArgumentException()
                )
            Assertions.assertEquals(response.statusCode, HttpStatus.OK)
            Assertions.assertEquals(response.body, SlackExceptionHandler.INTERNAL_ERROR_RESPONSE)
        }

        @Test
        @DisplayName("VerificationException")
        fun verificationException() {
            val response = SlackExceptionHandler()
                .handleVerificationException(VerificationException("Unverified"))

            Assertions.assertEquals(response.statusCode, HttpStatus.UNAUTHORIZED)
            Assertions.assertEquals(response.body, "Unverified")
        }

        @Test
        @DisplayName("RuntimeException")
        fun runtimeException() {
            val response = SlackExceptionHandler()
                .handleExceptionInternal(RuntimeException())

            Assertions.assertEquals(response.statusCode, HttpStatus.OK)
            Assertions.assertEquals(response.body, SlackExceptionHandler.INTERNAL_ERROR_RESPONSE)
        }
    }

    @Nested
    @DisplayName("Integration")
    internal class Integration {

        private val inMemoryTeamStore = InMemoryTeamStore()

        init {
            inMemoryTeamStore.put(Team.sample().copy(teamId = "sampleTeamId"))
        }

        @Test
        @DisplayName("Test Verification Exception")
        fun verificationException() {
            val verificationException = VerificationException("")
            val mockMvc = MockMvcBuilders
                .standaloneSetup(commandBroker(verificationException), SlackExceptionHandler())
                .setCustomArgumentResolvers(SlashCommandArgumentResolver("1"))
                .build()

            val parameterMap = LinkedMultiValueMap(SlashCommand.sample().toParameterMap())

            val timestamp = Instant.now()
            val generatedHmacHex = RequestTestUtils.generateHmacHex("", timestamp, "1")

            mockMvc.perform(
                post("/commands")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(parameterMap)
                    .header("x-slack-signature", generatedHmacHex)
                    .header("x-slack-request-timestamp", "${timestamp.epochSecond}")
                    .accept(MediaType.APPLICATION_JSON)
            )
                .andExpect {
                    Assertions.assertEquals(it.response.status, HttpStatus.UNAUTHORIZED.value())
                }
        }

        @Test
        @DisplayName("Test DialogValidationException")
        fun unknownException() {
            val runtimeException = RuntimeException()
            val mockMvc = MockMvcBuilders
                .standaloneSetup(commandBroker(runtimeException), SlackExceptionHandler())
                .setCustomArgumentResolvers(SlashCommandArgumentResolver("1"))
                .build()

            val parameterMap = LinkedMultiValueMap(SlashCommand.sample().toParameterMap())

            val timestamp = Instant.now()
            val generatedHmacHex = RequestTestUtils.generateHmacHex(
                RequestTestUtils.toFormUrlString(parameterMap),
                timestamp,
                "1"
            )

            mockMvc.perform(
                post("/commands")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(parameterMap)
                    .header("x-slack-signature", generatedHmacHex)
                    .header("x-slack-request-timestamp", "${timestamp.epochSecond}")
                    .accept(MediaType.APPLICATION_JSON)
            )
                .andExpect {
                    Assertions.assertEquals(HttpStatus.OK.value(), it.response.status)
                    Assertions.assertEquals(SlackExceptionHandler.INTERNAL_ERROR_RESPONSE, it.response.contentAsString)
                }
        }

        private fun commandBroker(exception: Exception): CommandBroker {
            return CommandBroker(listOf(ErrorCommand(exception)), inMemoryTeamStore, mockk())
        }

        class ErrorCommand(private val exception: Exception) : SlashCommandReceiver {

            override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
                throw exception
            }
        }
    }
}
