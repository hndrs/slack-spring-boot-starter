package com.kreait.slack.broker.exception

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.dialog.DialogErrorResponse
import com.kreait.slack.api.contract.jackson.sample
import com.kreait.slack.api.contract.jackson.toParameterMap
import com.kreait.slack.broker.RequestTestUtils
import com.kreait.slack.broker.broker.CommandBroker
import com.kreait.slack.broker.broker.InteractiveComponentBroker
import com.kreait.slack.broker.configuration.SlackCommandArgumentResolver
import com.kreait.slack.broker.extensions.sample
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.security.VerificationException
import com.kreait.slack.broker.store.InMemoryTeamStore
import com.kreait.slack.broker.store.Team
import com.nhaarman.mockitokotlin2.mock
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

@DisplayName("SlackExceptionHandler")
internal class SlackExceptionHandlerTests {

    @Nested
    @DisplayName("Unit")
    internal class Unit {

        @Test
        @DisplayName("DialogValidationException")
        fun dialogValidationException() {
            val response = SlackExceptionHandler("TestResponse")
                    .handleDialogValidationException(DialogValidationException(emptyList()))

            Assertions.assertEquals(response.statusCode, HttpStatus.OK)
            Assertions.assertEquals(response.body, DialogErrorResponse(emptyList()))
        }

        @Test
        @DisplayName("VerificationException")
        fun verificationException() {
            val response = SlackExceptionHandler("TestResponse")
                    .handleVerificationException(VerificationException("Unverified"))

            Assertions.assertEquals(response.statusCode, HttpStatus.UNAUTHORIZED)
            Assertions.assertEquals(response.body, "Unverified")
        }

        @Test
        @DisplayName("RuntimeException")
        fun runtimeException() {
            val response = SlackExceptionHandler("TestResponse")
                    .handleExceptionInternal(RuntimeException(), mock { })

            Assertions.assertEquals(response.statusCode, HttpStatus.OK)
            Assertions.assertEquals(response.body, "TestResponse")
        }

    }


    @Nested
    @DisplayName("Integration")
    internal class Integration {


        private val testResponse = "TestResponse"
        private val inMemoryTeamStore = InMemoryTeamStore()

        init {
            inMemoryTeamStore.put(Team.sample().copy(teamId = "sampleTeamId"))
        }


        @Test
        @DisplayName("Test Verification Exception")
        fun verificationException() {
            val verificationException = VerificationException("")
            val mockMvc = MockMvcBuilders
                    .standaloneSetup(commandBroker(verificationException), interactiveComponentBroker(verificationException), SlackExceptionHandler(testResponse))
                    .setCustomArgumentResolvers(SlackCommandArgumentResolver("1"))
                    .build()


            val parameterMap = LinkedMultiValueMap(SlackCommand.sample().toParameterMap())

            val timestamp = Instant.now()
            val generatedHmacHex = RequestTestUtils.generateHmacHex("", timestamp, "1")

            mockMvc.perform(post("/commands")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(parameterMap)
                    .header("x-slack-signature", generatedHmacHex)
                    .header("x-slack-request-timestamp", "${timestamp.epochSecond}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect {
                        Assertions.assertEquals(it.response.status, HttpStatus.UNAUTHORIZED.value())
                    }

        }

        @Test
        @DisplayName("Test DialogValidationException")
        fun dialogValidationException() {
            val dialogValidationException = DialogValidationException(listOf())
            val mockMvc = MockMvcBuilders
                    .standaloneSetup(commandBroker(dialogValidationException), interactiveComponentBroker(dialogValidationException), SlackExceptionHandler(testResponse))
                    .setCustomArgumentResolvers(SlackCommandArgumentResolver("1"))
                    .build()


            val parameterMap = LinkedMultiValueMap(SlackCommand.sample().toParameterMap())

            val timestamp = Instant.now()
            val generatedHmacHex = RequestTestUtils.generateHmacHex(RequestTestUtils.toFormUrlString(parameterMap), timestamp, "1")

            mockMvc.perform(post("/commands")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(parameterMap)
                    .header("x-slack-signature", generatedHmacHex)
                    .header("x-slack-request-timestamp", "${timestamp.epochSecond}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect {
                        Assertions.assertEquals(it.response.status, HttpStatus.OK.value())
                    }

        }

        @Test
        @DisplayName("Test DialogValidationException")
        fun unknownException() {
            val runtimeException = RuntimeException()
            val mockMvc = MockMvcBuilders
                    .standaloneSetup(commandBroker(runtimeException), interactiveComponentBroker(runtimeException), SlackExceptionHandler(testResponse))
                    .setCustomArgumentResolvers(SlackCommandArgumentResolver("1"))
                    .build()


            val parameterMap = LinkedMultiValueMap(SlackCommand.sample().toParameterMap())

            val timestamp = Instant.now()
            val generatedHmacHex = RequestTestUtils.generateHmacHex(RequestTestUtils.toFormUrlString(parameterMap), timestamp, "1")

            mockMvc.perform(post("/commands")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .params(parameterMap)
                    .header("x-slack-signature", generatedHmacHex)
                    .header("x-slack-request-timestamp", "${timestamp.epochSecond}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect {
                        Assertions.assertEquals(HttpStatus.OK.value(), it.response.status)
                        Assertions.assertEquals(testResponse, it.response.contentAsString)
                    }

        }


        private fun commandBroker(exception: Exception): CommandBroker {
            return CommandBroker(listOf(ErrorCommand(exception)), inMemoryTeamStore)
        }

        private fun interactiveComponentBroker(exception: Exception): InteractiveComponentBroker {
            return InteractiveComponentBroker(listOf(ErrorInteractiveResponse(exception)), inMemoryTeamStore)
        }


        class ErrorCommand(private val exception: Exception) : SlashCommandReceiver {

            override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
                throw exception
            }
        }

        class ErrorInteractiveResponse(private val exception: Exception) : InteractiveComponentReceiver {
            override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {
                throw exception
            }

        }

    }

}
