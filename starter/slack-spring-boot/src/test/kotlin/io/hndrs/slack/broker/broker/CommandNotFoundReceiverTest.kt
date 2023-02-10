package io.hndrs.slack.broker.broker

import com.slack.api.Slack
import com.slack.api.methods.request.chat.ChatPostEphemeralRequest
import io.hndrs.slack.broker.command.CommandNotFoundReceiver
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.store.team.Team
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

internal class CommandNotFoundReceiverTest {

    @Test
    @DisplayName("CommandNotFoundReceiver sends message with text")
    fun onReceiveSlashCommand() {
        val slot = slot<ChatPostEphemeralRequest>()
        val methods = spyk(Slack.getInstance().methods()) {
            every { chatPostEphemeral(capture(slot)) } returns mockk()
        }

        val expectedText = "Sample Text"
        val expectedUserId = "UserId"
        val expectedChannelId = "ChannelId"

        val command = SlashCommand.sample()
            .copy(
                channelId = expectedChannelId,
                userId = expectedUserId
            )

        CommandNotFoundReceiver(expectedText).onMismatchedSlashCommand(
            command,
            HttpHeaders.EMPTY,
            Team.sample(),
            methods
        )
        slot.captured.let {
            Assertions.assertEquals(expectedText, it.text)
            Assertions.assertEquals(expectedUserId, it.user)
            Assertions.assertEquals(expectedChannelId, it.channel)
        }
    }
}
