package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.contract.jackson.sample
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.broker.extensions.sample
import com.kreait.slack.broker.store.team.Team
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

internal class CommandNotFoundReceiverTest {

    @Test
    @DisplayName("CommandNotFoundReceiver sends message with text")
    fun onReceiveSlashCommand() {
        val mockSlackClient = MockSlackClient()
        mockSlackClient.chat().postEphemeral("").successResponse = SuccessfulPostEphemeralResponse.sample()
        mockSlackClient.chat().postEphemeral("").failureResponse = ErrorPostEphemeralResponse.sample()

        val expectedText = "Sample Text"
        val expectedUserId = "UserId"
        val expectedChannelId = "ChannelId"

        val slackCommand = SlackCommand.sample().copy(userId = expectedUserId, channelId = expectedChannelId)

        CommandNotFoundReceiver(mockSlackClient, expectedText).onReceiveSlashCommand(slackCommand, HttpHeaders.EMPTY, Team.sample())

        val expectedRequest = PostEphemeralRequest(
                channel = expectedChannelId,
                text = expectedText,
                user = expectedUserId)

        Assertions.assertEquals(expectedRequest, mockSlackClient.chat().postEphemeral("").params())
    }
}
