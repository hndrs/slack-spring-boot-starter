package io.hndrs.slack.broker.interactive

import io.hndrs.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * Interface which is used to receive slash-commands
 */
interface BlockActionReceiver {
    /**
     * Determines if the implementing receiver should handle the incoming block action
     *
     * @param payload the incoming [BlockActionPayload]
     */
    fun supports(payload: BlockActionPayload): Boolean = true

    /**
     * method that will only be invoked when [supports] returned true
     *
     * @param payload the incoming [BlockActionPayload]
     * @param team the extracted team with the access-token that is used for further actions
     */
    fun onBlockAction(payload: BlockActionPayload, headers: HttpHeaders, team: Team)
}
