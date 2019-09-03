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

    companion object {
        const val RPS_BLOCK_ID = "RPS_BLOCK"
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(PostMessageRequest("Choose your weapon",
                        blocks = listOf(
                                Block.Section(text = Text(Text.Type.PLAIN_TEXT, "choose your weapon")),
                                Block.Action(blockId = RPS_BLOCK_ID,
                                        elements = listOf(
                                                Element.Button(Text(Text.Type.PLAIN_TEXT, WEAPONS.ROCK.weaponName), actionId = WEAPONS.ROCK.actionId),
                                                Element.Button(Text(Text.Type.PLAIN_TEXT, WEAPONS.PAPER.weaponName), actionId = WEAPONS.PAPER.actionId),
                                                Element.Button(Text(Text.Type.PLAIN_TEXT, WEAPONS.SCISSORS.weaponName), actionId = WEAPONS.SCISSORS.actionId)
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

enum class WEAPONS(val weaponName: String, val actionId: String) {
    ROCK("Rock", "ROCK_ACTION"),
    PAPER("Paper", "PAPER_ACTION"),
    SCISSORS("Scissors", "SCISSORS_ACTION")
}