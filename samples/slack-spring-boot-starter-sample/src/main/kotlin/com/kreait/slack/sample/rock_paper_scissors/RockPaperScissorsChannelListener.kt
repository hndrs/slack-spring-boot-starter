package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.broker.receiver.TypedEventReceiver
import com.kreait.slack.broker.store.team.Team
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class RockPaperScissorsChannelListener @Autowired constructor(private val rpsGameHandler: RPSGameHandler,
                                                              private val slackClient: SlackClient) : TypedEventReceiver<Event.Generic> {

    override fun supportsEvent(slackEvent: SlackEvent<Event>): Boolean {
        val data = (slackEvent.event as Event.Generic).data
        return (data["type"] == "message"
                && ((data["text"] == "rock")
                || (data["text"] == "paper")
                || (data["text"] == "scissors")
                || (data["text"] == "rock paper scissors")))
    }

    override fun onReceive(slackEvent: SlackEvent<Event.Generic>, headers: HttpHeaders, team: Team) {
        val eventMessage = (slackEvent.event.data["text"] as String).trim()

        if (eventMessage == "rock") {
            rpsGameHandler.dmHandler(WEAPONS.ROCK, team, slackEvent)
        }
        if (eventMessage == "paper") {
            rpsGameHandler.dmHandler(WEAPONS.PAPER, team, slackEvent)
        }
        if (eventMessage == "scissors") {
            rpsGameHandler.dmHandler(WEAPONS.SCISSORS, team, slackEvent)
        }
        if (eventMessage == "rock paper scissors") {
            this.slackClient.chat().postEphemeral(team.bot.accessToken)
                    .with(PostEphemeralRequest("Choose your weapon!",
                            user = slackEvent.event.data["user"].toString(),
                            blocks = RPSGameHandler.blocks,
                            channel = slackEvent.event.data["channel"].toString()))
                    .onSuccess {
                        println(it)
                    }
                    .onFailure {
                        println(it)
                    }.invoke()
        }
    }
}
