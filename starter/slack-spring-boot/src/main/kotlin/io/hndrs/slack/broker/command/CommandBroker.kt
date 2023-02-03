package io.hndrs.slack.broker.command

import com.slack.api.Slack
import io.hndrs.slack.broker.receiver.MismatchCommandReceiver
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.TeamStore
import io.hndrs.slack.broker.util.methods
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * CommandBroker that forwards incoming [SlashCommand]s to all [SlashCommandReceiver]s
 */
@SuppressWarnings("detekt:TooGenericExceptionCaught")
@RestController
class CommandBroker constructor(
    private val slackCommandReceivers: List<SlashCommandReceiver>,
    private val teamStore: TeamStore,
    private val slack: Slack,
    private val mismatchCommandReceiver: MismatchCommandReceiver? = null,
) {

    /**
     * Endpoint that receives the commands
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
        "/commands",
        consumes = ["application/x-www-form-urlencoded"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun receiveCommand(@Command slashCommand: SlashCommand, @RequestHeader headers: HttpHeaders) {
        val team = this.teamStore.findById(slashCommand.teamId)
        slackCommandReceivers
            .filter { it.supportsCommand(slashCommand) }
            .sortedBy { it.order() }
            .ifEmpty {
                // TODO figure out how to deal with mismatching commands when SL4J logger is active
                mismatchCommandReceiver?.onMismatchedSlashCommand(slashCommand, headers, team, slack.methods(team))
                listOf()
            }
            .forEach { commandReceiver ->
                try {
                    commandReceiver.onSlashCommand(slashCommand, headers, team)
                } catch (e: Exception) {
                    if (commandReceiver.shouldThrowException(e)) {
                        throw e
                    }
                }
            }
    }
}
