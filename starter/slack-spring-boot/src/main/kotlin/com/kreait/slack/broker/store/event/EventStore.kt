package com.kreait.slack.broker.store.event

import com.kreait.slack.api.contract.jackson.EventRequest

interface EventStore {

    fun exists(id: String): Boolean

    fun put(event: EventRequest)

}
