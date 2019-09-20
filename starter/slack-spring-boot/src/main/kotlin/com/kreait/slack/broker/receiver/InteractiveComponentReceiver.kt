package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.broker.store.team.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

/**
 * Receiver where InteractiveComponents will be forwarded to
 *
 * @param Type the type of Response the implementing receiver should handle
 */
interface InteractiveComponentReceiver<Type : InteractiveComponentResponse> {

    /**
     * Determines if the implementing receiver should handle the incoming response
     *
     * @param interactiveComponentResponse the incoming response
     */
    fun supportsInteractiveMessage(interactiveComponentResponse: Type): Boolean = true

    /**
     * method that will only be invoked when [supportsInteractiveMessage] returned true
     *
     * @param interactiveComponentResponse the incoming interactive component response
     * @param team the extracted team with the access-token that is used for further actions
     */
    fun onReceiveInteractiveMessage(interactiveComponentResponse: Type, headers: HttpHeaders, team: Team)

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
