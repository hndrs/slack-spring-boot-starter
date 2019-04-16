package io.olaph.slack.broker.receiver

import io.olaph.slack.broker.extensions.sample
import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import io.olaph.slack.dto.jackson.sample
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

        val expectedRequest = SlackPostEphemeralRequest(
                channel = expectedChannelId,
                text = expectedText,
                user = expectedUserId)

        Assertions.assertEquals(expectedRequest, mockSlackClient.chat().postEphemeral("").params())
    }
}
