package io.hndrs.slack.broker.store.event

import io.hndrs.slack.broker.event.EventRequest

/**
 * Interface that is used to store received events
 */
interface EventStore {

    /**
     * determines if the event is already existing in the store
     *
     * @param id the id of the event
     * @return true when the event already exists
     */
    fun exists(id: String): Boolean

    /**
     * adds a new event to the store
     *
     * @param event the received event
     */
    fun put(event: EventRequest)
}
