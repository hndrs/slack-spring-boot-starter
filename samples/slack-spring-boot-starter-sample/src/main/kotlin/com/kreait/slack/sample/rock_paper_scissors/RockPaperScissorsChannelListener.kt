package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.messaging.Element
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.Team
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class RockPaperScissorsChannelListener @Autowired constructor(private val rpsGameHandler: RPSGameHandler,
                                                              private val slackClient: SlackClient) : EventReceiver {

    override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return (slackEvent.event["type"] == "message"
                && ((slackEvent.event["text"] == "rock")
                || (slackEvent.event["text"] == "paper")
                || (slackEvent.event["text"] == "scissors")
                || (slackEvent.event["text"] == "rock paper scissors")))
    }

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        val eventMessage = slackEvent.event["text"] as String

        if (eventMessage =="rock") {
            rpsGameHandler.dmHandler(WEAPONS.ROCK, team, slackEvent)
        }
        if (eventMessage == "paper") {
            rpsGameHandler.dmHandler(WEAPONS.PAPER, team, slackEvent)
        }
        if (eventMessage == "scissors") {
            rpsGameHandler.dmHandler(WEAPONS.SCISSORS, team, slackEvent)
        }
        if (eventMessage =="rock paper scissors") {
            this.slackClient.chat().postEphemeral(team.bot.accessToken)
                    .with(PostEphemeralRequest("Choose your weapon!",
                            user = slackEvent.event["user"].toString(),
                            blocks = listOf(
                                    Block.Section(text = Text(Text.Type.PLAIN_TEXT, "choose your weapon"), blockId = "weapons_block"),
                                    Block.Action(blockId = RockPaperScissorsCommandReceiver.RPS_BLOCK_ID,
                                            elements = listOf(
                                                    Element.Button(text = Text(Text.Type.PLAIN_TEXT, WEAPONS.ROCK.weaponName), actionId = WEAPONS.ROCK.actionId),
                                                    Element.Button(text = Text(Text.Type.PLAIN_TEXT, WEAPONS.PAPER.weaponName), actionId = WEAPONS.PAPER.actionId),
                                                    Element.Button(text = Text(Text.Type.PLAIN_TEXT, WEAPONS.SCISSORS.weaponName), actionId = WEAPONS.SCISSORS.actionId)
                                            )
                                    )
                            ),
                            channel = slackEvent.event["channel"].toString()))
                    .onSuccess {
                        println(it)
                    }
                    .onFailure {
                        println(it)
                    }.invoke()
        }
    }
}