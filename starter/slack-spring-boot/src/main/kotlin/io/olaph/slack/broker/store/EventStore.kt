package io.olaph.slack.broker.store

import io.olaph.slack.dto.jackson.EventRequest

interface EventStore {

    fun exists(id: String): Boolean

    fun put(event: EventRequest)

}
