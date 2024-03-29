package io.hndrs.slack.broker.event.http

import io.hndrs.slack.broker.event.EventHandler
import io.hndrs.slack.broker.event.EventRequest
import io.hndrs.slack.broker.event.SlackChallenge
import io.hndrs.slack.broker.event.SlackEvent
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.team.Team
import io.hndrs.slack.broker.store.team.TeamStore
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * EventBroker that forwards all incoming [SlackEvent]s to the [EventHandler]s
 *
 * @property handlers
 * @property teamStore
 * @property eventStore
 * @property metricsCollector
 */
@SuppressWarnings("detekt:TooGenericExceptionCaught")
@RestController
class EventEndpoint constructor(
    private val handlers: List<EventHandler>,
    private val teamStore: TeamStore,
    private val eventStore: EventStore,
) {

    /**
     * Endpoint that receives the events
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
        "/events",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun receiveEvents(@Event event: EventRequest, @RequestHeader headers: HttpHeaders): Map<String, String> {
        if (event is SlackChallenge) {
            return mapOf(Pair("challenge", event.challenge))
        } else if (event is SlackEvent && shouldInvoke(event)) {
            val team = this.teamStore.findById(event.teamId)
            this.invoke(event, headers, team)
        }

        return mapOf()
    }

    /**
     * Checks if an event should trigger the receiver chain.
     * returns false if the underlying eventstore determines that an event already has been
     * received
     */
    private fun shouldInvoke(event: SlackEvent): Boolean {
        val exists = this.eventStore.exists(event.eventId)
        return if (exists) {
            when {
                LOG.isDebugEnabled -> LOG.debug("Event duplicate:{}", event)
            }
            false
        } else {
            when {
                LOG.isDebugEnabled -> LOG.debug("New Event:{}", event)
            }
            this.eventStore.put(event)
            true
        }
    }

    /**
     * Invokes the receiver chain
     */
    private fun invoke(event: SlackEvent, headers: HttpHeaders, team: Team) {
        this.handlers
            .filter { receiver ->
                val supportsEvent = receiver.supportsEvent(event)

                when {
                    LOG.isDebugEnabled -> LOG.debug(
                        "EventReceiver:{}\nSupportsEvent:{}\nEvent:{} ",
                        receiver::class,
                        supportsEvent,
                        event
                    )
                }
                supportsEvent
            }
            .sortedBy { it.order() }
            .forEach { receiver ->
                try {
                    receiver.onReceiveEvent(event, headers, team)
                } catch (e: Exception) {
                    if (receiver.shouldThrowException(e)) {
                        throw e
                    }
                }
            }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(EventEndpoint::class.java)
    }
}
