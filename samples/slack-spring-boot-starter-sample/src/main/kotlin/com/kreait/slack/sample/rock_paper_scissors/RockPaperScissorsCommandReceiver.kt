package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.messaging.Element
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.Team
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component


@Component
class RockPaperScissorsCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {

    companion object {
        const val RPS_BLOCK_ID = "RPS_BLOCK"
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat().postEphemeral(team.bot.accessToken)
                .with(PostEphemeralRequest("Choose your weapon",
                        user = slackCommand.userId,
                        blocks = listOf(
                                Block.Section(text = Text(Text.Type.PLAIN_TEXT, "choose your weapon"), blockId = "weapons_block"),
                                Block.Action(blockId = RPS_BLOCK_ID,
                                        elements = listOf(
                                                Element.Button(text = Text(Text.Type.PLAIN_TEXT, WEAPONS.ROCK.weaponName), actionId = WEAPONS.ROCK.actionId),
                                                Element.Button(text = Text(Text.Type.PLAIN_TEXT, WEAPONS.PAPER.weaponName), actionId = WEAPONS.PAPER.actionId),
                                                Element.Button(text = Text(Text.Type.PLAIN_TEXT, WEAPONS.SCISSORS.weaponName), actionId = WEAPONS.SCISSORS.actionId)
                                        )
                                )
                        ),
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
