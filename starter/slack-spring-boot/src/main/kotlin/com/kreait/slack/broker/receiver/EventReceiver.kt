package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.broker.store.team.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that should be implemented to react to incoming events
 */
interface EventReceiver {

    /**
     * Method that determines if the incoming event should be handled by the implementing receiver
     *
     * @param slackEvent the incoming event
     */
    fun supportsEvent(slackEvent: SlackEvent): Boolean = true

    /**
     * will be executed if [supportsEvent] returned true
     *
     * @param slackEvent the incoming event
     * @param team the team with the access-token which can be used for further actions
     */
    fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team)

    /**
     * Method that determines if an exception in this receiver should be thrown
     * This will cause that the [EventBroker] will not execute any other event receiver after an error
     *
     * @param exception the exception that occurred
     */
    fun shouldThrowException(exception: Exception): Boolean = false

    /**
     * receivers will be sorted ascending by this order
     */
    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}
