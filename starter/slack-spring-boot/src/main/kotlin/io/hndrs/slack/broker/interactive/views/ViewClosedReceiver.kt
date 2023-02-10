package io.hndrs.slack.broker.interactive.views

import io.hndrs.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * Interface which is used to receive slash-commands
 */
interface ViewClosedReceiver {
    /**
     * Determines if the implementing receiver should handle the incoming view closed event
     *
     * @param payload the incoming [ViewClosedPayload]
     */
    fun supports(payload: ViewClosedPayload): Boolean = true

    /**
     * method that will only be invoked when [supports] returned true
     *
     * @param payload the incoming [BlockActionPayload]
     * @param team the extracted team with the access-token that is used for further actions
     */
    fun onViewClosed(payload: ViewClosedPayload, headers: HttpHeaders, team: Team)

}
