package io.hndrs.slack.broker.broker

import com.slack.api.RequestConfigurator
import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostEphemeralRequest
import io.hndrs.slack.broker.command.DefaultUnknownCommandHandler
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.store.team.Team
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

internal class CommandNotFoundReceiverTest {

    @Test
    @DisplayName("CommandNotFoundReceiver sends message with text")
    fun onReceiveSlashCommand() {
        val methodsClient = mockk<MethodsClient>(relaxed = true)
        val slack = spyk(Slack.getInstance()) {
            every { methods(any()) } returns methodsClient
        }

        val expectedText = "Sample Text"
        val expectedUserId = "UserId"
        val expectedChannelId = "ChannelId"

        val command = SlashCommand.sample()
            .copy(
                channelId = expectedChannelId,
                userId = expectedUserId
            )

        DefaultUnknownCommandHandler(slack, expectedText).onUnknownCommand(
            command,
            HttpHeaders.EMPTY,
            Team.sample(),
        )

        verify {
            methodsClient.chatPostEphemeral(
                withArg<RequestConfigurator<ChatPostEphemeralRequest.ChatPostEphemeralRequestBuilder>> {
                    val reqBuilder = ChatPostEphemeralRequest.builder()
                    it.configure(reqBuilder)
                    val req = reqBuilder.build()
                    req.channel shouldBe expectedChannelId
                    req.text shouldBe expectedText
                    req.user shouldBe expectedUserId
                }
            )
        }
    }
}
