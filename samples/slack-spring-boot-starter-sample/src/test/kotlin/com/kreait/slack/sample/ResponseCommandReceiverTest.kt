package com.kreait.slack.sample

import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.sample
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.broker.store.team.Team
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders


class ResponseCommandReceiverTest {

    @Test
    @DisplayName("test response command receiver")
    fun testSuccess() {
        // create mock slack client
        val mockSlackClient = MockSlackClient()
        // set the successful response for the respond().message() method
        mockSlackClient.respond().message("www.test.com").successResponse = Unit
        val responseHandler = mock<ResponseHandler>()
        // provide mock client to receiver implementation
        val commandReceiver = ResponseCommandReceiver(mockSlackClient, responseHandler)
        // call the receiver
        commandReceiver.onReceiveSlashCommand(
            SlackCommand.sample().copy(command = "/response", channelId = "test-channel", responseUrl = "www.test.com"),
            HttpHeaders.EMPTY, Team(
                "", "",
                Team.Bot("", "test-token")
            )
        )
        // verify logic has been executed as expected
        verify(responseHandler, times(1)).successResponse("test-channel", "test-token")
        verify(responseHandler, times(0)).failureResponse("test-channel", "test-token")
    }

    @Test
    @DisplayName("test response command receiver")
    fun testFailure() {
        // create mock slack client
        val mockSlackClient = MockSlackClient()
        // set the failure response for the respond().message() method
        mockSlackClient.respond().message("www.test.com").failureResponse = Unit
        val responseHandler = mock<ResponseHandler>()
        // provide mock client to receiver implementation
        val commandReceiver = ResponseCommandReceiver(mockSlackClient, responseHandler)
        // call the receiver
        commandReceiver.onReceiveSlashCommand(
            SlackCommand.sample().copy(command = "/response", channelId = "test-channel", responseUrl = "www.test.com"),
            HttpHeaders.EMPTY,
            Team("", "", Team.Bot("", "test-token"))
        )
        // verify logic has been executed as expected
        verify(responseHandler, times(0)).successResponse("test-channel", "test-token")
        verify(responseHandler, times(1)).failureResponse("test-channel", "test-token")
    }
}
