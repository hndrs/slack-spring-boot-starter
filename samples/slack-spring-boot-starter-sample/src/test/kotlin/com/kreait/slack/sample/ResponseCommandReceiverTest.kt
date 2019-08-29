package com.kreait.slack.sample

import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.sample
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.broker.store.Team
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders


class ResponseCommandReceiverTest() {

    @Test
    @DisplayName("test response command receiver")
    fun testSuccess() {
        val mockSlackClient = MockSlackClient()
        mockSlackClient.respond().message("www.test.com").successResponse = Unit
        val responseHandler = mock<ResponseHandler>()
        val commandReceiver = ResponseCommandReceiver(mockSlackClient, responseHandler)
        commandReceiver.onReceiveSlashCommand(SlackCommand.sample().copy(command = "/response", channelId = "test-channel", responseUrl = "www.test.com"),
                HttpHeaders.EMPTY, Team("", "", null,
                Team.Bot("", "test-token")))
        verify(responseHandler, times(1)).successResponse("test-channel", "test-token")
        verify(responseHandler, times(0)).failureResponse("test-channel", "test-token")
    }

    @Test
    @DisplayName("test response command receiver")
    fun testFailure() {
        val mockSlackClient = MockSlackClient()
        mockSlackClient.respond().message("www.test.com").failureResponse = Unit
        val responseHandler = mock<ResponseHandler>()
        val commandReceiver = ResponseCommandReceiver(mockSlackClient, responseHandler)
        commandReceiver.onReceiveSlashCommand(SlackCommand.sample().copy(command = "/response", channelId = "test-channel", responseUrl = "www.test.com"),
                HttpHeaders.EMPTY,
                Team("", "", null, Team.Bot("", "test-token")))
        verify(responseHandler, times(0)).successResponse("test-channel", "test-token")
        verify(responseHandler, times(1)).failureResponse("test-channel", "test-token")
    }
}