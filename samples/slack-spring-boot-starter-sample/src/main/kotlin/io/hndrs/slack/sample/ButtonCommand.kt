package io.hndrs.slack.sample

import com.slack.api.Slack
import com.slack.api.methods.kotlin_extension.request.chat.blocks
import com.slack.api.model.kotlin_extension.block.element.ButtonStyle
import io.hndrs.slack.broker.command.CommandHandler
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.util.*

@Component
class ButtonCommand(
    private val slack: Slack,
) : CommandHandler {
    override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {

        slack.methods().chatPostMessage {

            it.blocks {
                actions {
                    button {
                        actionId(UUID.randomUUID().toString())
                        style(ButtonStyle.PRIMARY)
                        text("Primary Button")
                    }
                    button {
                        actionId(UUID.randomUUID().toString())
                        style(ButtonStyle.DANGER)
                        text("Danger Button")
                    }
                    button {
                        actionId(UUID.randomUUID().toString())
                        text("Default Button")
                    }
                }
            }
                .text("Components")
                .channel(slashCommand.channelId)
                .token(team.accessToken)

        }.also {
            LOG.info("{}", it)
        }
    }

    override fun supportsCommand(slashCommand: SlashCommand): Boolean {
        return slashCommand.command == "/demo" && slashCommand.text == "buttons"
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ButtonCommand::class.java)
    }
}
