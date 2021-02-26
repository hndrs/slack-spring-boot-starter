package io.hndrs.slack.sample.rock_paper_scissors

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.event.SlackEvent
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import io.hndrs.slack.broker.receiver.EventReceiver
import io.hndrs.slack.broker.store.team.Team
import io.hndrs.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class RockPaperScissorsChannelListener @Autowired constructor(
    private val rpsGameHandler: RPSGameHandler,
    private val slackClient: io.hndrs.slack.api.SlackClient
) : EventReceiver {

    override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        val data = slackEvent.event
        return (data["type"] == "message"
                && ((data["text"] == "rock")
                || (data["text"] == "paper")
                || (data["text"] == "scissors")
                || (data["text"] == "rock paper scissors")))
    }

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        val eventMessage = (slackEvent.event["text"] as String).trim()

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
                .with(
                    PostEphemeralRequest(
                        "Choose your weapon!",
                        user = slackEvent.event["user"].toString(),
                        blocks = RPSGameHandler.blocks,
                        channel = slackEvent.event["channel"].toString()
                    )
                )
                .onSuccess {
                    println(it)
                }
                .onFailure {
                    println(it)
                }.invoke()
        }
    }
}
