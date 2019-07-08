package io.olaph.slack.broker.broker

import io.olaph.slack.broker.configuration.InteractiveResponse
import io.olaph.slack.broker.exception.ExceptionChain
import io.olaph.slack.broker.exception.MustThrow
import io.olaph.slack.broker.metrics.InteractiveComponentMetricsCollector
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.dto.jackson.InteractiveComponentResponse
import io.olaph.slack.dto.jackson.group.interactive_component.InteractiveComponentMessageResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class InteractiveComponentBroker constructor(private val slackInteractiveComponentReceivers: List<InteractiveComponentReceiver>,
                                             private val teamStore: TeamStore,
                                             private val metricsCollector: InteractiveComponentMetricsCollector? = null) {

    companion object {
        val LOG = LoggerFactory.getLogger(InteractiveComponentBroker::class.java)
    }

    @PostMapping("/interactive-components", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveEvents(@InteractiveResponse interactiveComponentResponse: InteractiveComponentResponse, @RequestHeader headers: HttpHeaders): ResponseEntity<InteractiveComponentMessageResponse> {
        this.metricsCollector?.responseReceived()

        val exceptionChain = ExceptionChain()

        val team = this.teamStore.findById(interactiveComponentResponse.team.id)
        slackInteractiveComponentReceivers
                .filter { it.supportsInteractiveMessage(interactiveComponentResponse) }
                .forEach { receiver ->
                    try {
                        this.metricsCollector?.receiverExecuted()
                        receiver.onReceiveInteractiveMessage(interactiveComponentResponse, headers, team)
                    } catch (e: Exception) {
                        this.metricsCollector?.receiverExecutionError()
                        if(e !is MustThrow) LOG.error("{}", e)
                        exceptionChain.add(e)
                    }
                }

        exceptionChain.trigger()

        return if (interactiveComponentResponse.type == "interactive_message") {
            ResponseEntity(InteractiveComponentMessageResponse(deleteOriginal = true), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.OK)
        }
    }
}
