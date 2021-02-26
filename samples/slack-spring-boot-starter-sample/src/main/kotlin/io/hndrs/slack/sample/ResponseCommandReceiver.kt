package io.hndrs.slack.sample

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.SlackCommand
import io.hndrs.slack.api.contract.jackson.group.respond.RespondMessageRequest
import io.hndrs.slack.api.contract.jackson.group.respond.ResponseType
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ResponseCommandReceiver @Autowired constructor(
    private val slackClient: io.hndrs.slack.api.SlackClient,
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
