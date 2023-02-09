package io.hndrs.slack.sample

import com.slack.api.Slack
import com.slack.api.methods.kotlin_extension.request.chat.blocks
import com.slack.api.model.kotlin_extension.block.element.ButtonStyle
import com.slack.api.model.kotlin_extension.view.blocks
import com.slack.api.model.view.ViewTitle
import com.slack.api.model.view.Views
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.command.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class DemoSlashCommand(
    private val slack: Slack,
) : SlashCommandReceiver {
    override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {

        when (slashCommand.text.trim()) {
            "ic" -> postInteractiveComponent(slashCommand, team)
            "modal" -> postInteractiveModal(slashCommand, team)
        }

    }

    private fun postInteractiveModal(slashCommand: SlashCommand, team: Team) {

        val response = slack.methods().viewsOpen {
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
        }

        println(response)
    }

    private fun postInteractiveComponent(slashCommand: SlashCommand, team: Team) {
        val chatPostMessage = slack.methods().chatPostMessage {

            it.blocks {
                actions {
                    button {
                        actionId("DemoButton")
                        style(ButtonStyle.PRIMARY)
                        text("Demo Button")
                    }
                }
            }
                .text("You got it!!")
                .channel(slashCommand.channelId)
                .token(team.accessToken)

        }

        LOG.info("{}", chatPostMessage)
    }

    override fun supportsCommand(slashCommand: SlashCommand): Boolean {
        return slashCommand.command == "/demo"
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(DemoSlashCommand::class.java)
    }
}
