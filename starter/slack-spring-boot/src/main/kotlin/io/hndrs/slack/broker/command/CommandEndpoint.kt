package io.hndrs.slack.broker.command

import io.hndrs.slack.broker.receiver.SL4JLoggingHandler
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * CommandBroker that forwards incoming [SlashCommand]s to all [CommandHandler]s
 */
@SuppressWarnings("detekt:TooGenericExceptionCaught")
@RestController
class CommandEndpoint constructor(
    private val slackCommandReceivers: List<CommandHandler>,
    private val teamStore: TeamStore,
    private val mismatchCommandReceiver: UnknownCommandHandler? = null,
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
            .also {
                if (it.isEmpty() || it.containsOnlySL4JLogger()) {
                    mismatchCommandReceiver?.onUnknownCommand(slashCommand, headers, team)
                }
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

    private fun List<CommandHandler>.containsOnlySL4JLogger(): Boolean {
        return this.size == 1 && this.first() is SL4JLoggingHandler
    }
}
