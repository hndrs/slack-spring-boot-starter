package io.olaph.slack.broker.broker

import io.olaph.slack.broker.configuration.Event
import io.olaph.slack.broker.exception.ExceptionChain
import io.olaph.slack.broker.exception.MustThrow
import io.olaph.slack.broker.metrics.EventMetricsCollector
import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.dto.jackson.EventRequest
import io.olaph.slack.dto.jackson.SlackChallenge
import io.olaph.slack.dto.jackson.SlackEvent
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EventBroker constructor(private val slackEventReceivers: List<EventReceiver>,
                              private val teamStore: TeamStore,
                              private val metricsCollector: EventMetricsCollector? = null) {

    companion object {
        val LOG = LoggerFactory.getLogger(EventBroker::class.java)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/events", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveEvents(@Event event: EventRequest, @RequestHeader headers: HttpHeaders): Map<String, String> {

        this.metricsCollector?.eventsReceived()
        val exceptionChain = ExceptionChain()

        if (event is SlackChallenge) {
            return mapOf(Pair("challenge", event.challenge))
        } else if (event is SlackEvent) {
            val team = teamStore.findById(event.teamId)
            slackEventReceivers
                    .filter { receiver ->
                        val supportsEvent = receiver.supportsEvent(event)

                        when {
                            LOG.isDebugEnabled -> LOG.debug("EventReceiver:{}\nSupportsEvent:{}\nEvent:{} ", receiver::class, supportsEvent, event)
                        }
                        supportsEvent
                    }
                    .forEach { receiver ->
                        try {
                            this.metricsCollector?.receiverExecuted()
                            receiver.onReceiveEvent(event, headers, team)
                        } catch (e: Exception) {
                            this.metricsCollector?.receiverExecutionError()
                            if (e !is MustThrow) LOG.error("Exception", e)
                            exceptionChain.add(e)
                        }
                    }
        }

        exceptionChain.trigger()

        return mapOf()
    }
}
