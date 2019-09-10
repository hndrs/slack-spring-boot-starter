package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.Team
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class RockPaperScissorsChannelListener @Autowired constructor(private val rpsGameHandler: RPSGameHandler) : EventReceiver {

    override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return slackEvent.event["type"] == "message"
    }

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        println("Event was ${slackEvent.event["type"]}")

        val eventMessage = slackEvent.event["text"] as String

        if (eventMessage.toLowerCase().contains("rock")) {
            println("It's a rock!")
            rpsGameHandler.dmHandler(eventMessage, team, slackEvent)
        }
        if (eventMessage.toLowerCase().contains("paper")) {
            println("It's paper!")
            rpsGameHandler.dmHandler(eventMessage, team, slackEvent)
        }
        if (eventMessage.toLowerCase().contains("scissors")) {
            println("It's scissors!")
            rpsGameHandler.dmHandler(eventMessage, team, slackEvent)
        }

        println("EventMessage was $eventMessage")
    }
}