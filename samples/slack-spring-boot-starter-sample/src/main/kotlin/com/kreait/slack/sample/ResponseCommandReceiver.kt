package com.kreait.slack.sample

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.ResponseType
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ResponseCommandReceiver @Autowired constructor(
    private val slackClient: SlackClient,
    private val responseHandler: ResponseHandler
) : SlashCommandReceiver {
    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/response")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.respond().message(slackCommand.responseUrl)
            .with(RespondMessageRequest(text = "Request successfull!", responseType = ResponseType.EPHEMERAL))
            .onSuccess {
                responseHandler.successResponse(slackCommand.channelId, team.bot.accessToken)
            }
            .onFailure {
                responseHandler.failureResponse(slackCommand.channelId, team.bot.accessToken)
            }
            .invoke()
    }
}
