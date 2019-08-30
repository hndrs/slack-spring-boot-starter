package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.messaging.Element
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class RockPaperScissorsCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(PostMessageRequest("Choose your weapon",
                        blocks = listOf(
                                Block.Action(blockId = "testBlock",
                                        elements = listOf(
                                                Element.Button(Text(Text.Type.PLAIN_TEXT, WEAPONS.ROCK.weaponName), actionId = "1"),
                                                Element.Button(Text(Text.Type.PLAIN_TEXT, WEAPONS.ROCK.weaponName), actionId = "2"),
                                                Element.Button(Text(Text.Type.PLAIN_TEXT, WEAPONS.SCISSORS.weaponName), actionId = "3")
                                        ))),
                        channel = slackCommand.channelId))
                .onSuccess {
                    println(it)
                }
                .onFailure {
                    println(it)
                }.invoke()
    }

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/rock-paper-scissors")
    }
}

enum class WEAPONS(val weaponName: String, val value: String, val actionId: String) {
    ROCK("Rock", "rock", "ROCK_ACTION"),
    PAPER("Paper", "paper", "PAPER_ACTION"),
    SCISSORS("Scissors", "scissors", "SCISSORS_ACTION")
}