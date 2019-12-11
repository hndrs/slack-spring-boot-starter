package com.kreait.slack.broker.receiver.adapter

import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.receiver.TypedEventReceiver

abstract class TypedEventReceiverAdapter<T : Event>(private vararg val eventType: String) : TypedEventReceiver<T> {

    final override fun supportsEvent(slackEvent: SlackEvent<Event>): Boolean {
        return eventType.any(slackEvent::isOfType)
    }
}
