package io.hndrs.slack.sample

import com.slack.api.Slack
import com.slack.api.model.kotlin_extension.block.element.ButtonStyle
import com.slack.api.model.kotlin_extension.view.blocks
import com.slack.api.model.view.ViewTitle
import com.slack.api.model.view.Views
import io.hndrs.slack.broker.command.CommandHandler
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class OpenModalCommand(
    private val slack: Slack,
) : CommandHandler {
    override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
        slack.methods().viewsOpen {
            it.view(
                Views.view {
                    it.blocks {
                        actions {
                            button {
                                actionId("DemoModal")
                                style(ButtonStyle.PRIMARY)
                                text("Demo Button")
                            }
                        }
                    }
                        .notifyOnClose(true)
                        .type("modal")
                        .title(ViewTitle.builder().type("plain_text").text("Demo Modal").build())
                }
            )
                .triggerId(slashCommand.triggerId)
                .token(team.accessToken)
        }.also {
            LOG.info("{}", it)
        }
    }

    override fun supportsCommand(slashCommand: SlashCommand): Boolean {
        return slashCommand.command == "/demo" && slashCommand.text.lowercase().trim() == "modal"
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(OpenModalCommand::class.java)
    }
}
