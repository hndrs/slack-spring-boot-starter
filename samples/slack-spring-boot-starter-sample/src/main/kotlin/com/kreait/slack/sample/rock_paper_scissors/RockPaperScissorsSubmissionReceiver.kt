package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

class RockPaperScissorsSubmissionReceiver @Autowired constructor(private val slackClient: SlackClient) : InteractiveComponentReceiver {
    override fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse): Boolean {
        return interactiveComponentResponse.actions?.let {
            return (it.first().blockId == RockPaperScissorsCommandReceiver.RPS_BLOCK_ID) && interactiveComponentResponse.type == "block_actions"
        } ?: false
    }

    override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {
        val selection = interactiveComponentResponse.actions?.first()?.text?.text
        this.slackClient.chat().delete(team.bot.accessToken)
                .with(ChatDeleteRequest(channel = interactiveComponentResponse.channel.id, timestamp = interactiveComponentResponse.message!!.timestamp))
                .onSuccess { println("deleted mesage $it") }
                .onFailure { println(it) }
                .invoke()
        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(PostMessageRequest(text = "you chose $selection", channel = interactiveComponentResponse.channel.id))
                .onSuccess { println(it) }
                .onFailure { println(it) }
                .invoke()
    }
}