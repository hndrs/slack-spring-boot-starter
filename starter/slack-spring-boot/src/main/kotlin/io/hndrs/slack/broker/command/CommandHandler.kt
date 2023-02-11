package io.hndrs.slack.broker.command

import io.hndrs.slack.broker.store.team.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

/**
 * Interface which is used to receive slash-commands
 */
interface CommandHandler {
    /**
     * Determines if the implementing receiver should handle the incoming command
     *
     * @param slashCommand the incoming command
     */
    fun supportsCommand(slashCommand: SlashCommand): Boolean = true

    /**
     * method that will only be invoked when [supportsCommand] returned true
     *
     * @param slashCommand the incoming command
     * @param team the extracted team with the access-token that is used for further actions
     */
    fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team)

    /**
     * Method that determines if an exception in this receiver should be thrown
     * This will cause that the [InteractiveComponentBroker] will not execute any other receiver after an error
     *
     * @param exception the exception that occurred
     */
    fun shouldThrowException(exception: Exception): Boolean = false

    /**
     * receivers will be sorted ascending by this order
     */
    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}
