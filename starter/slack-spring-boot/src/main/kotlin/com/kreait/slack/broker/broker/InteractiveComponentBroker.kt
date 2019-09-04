package com.kreait.slack.broker.broker

import com.kreait.slack.api.contract.jackson.BlockActions
import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.InteractiveMessage
import com.kreait.slack.api.contract.jackson.group.interactive_component.InteractiveComponentMessageResponse
import com.kreait.slack.broker.configuration.InteractiveResponse
import com.kreait.slack.broker.metrics.InteractiveComponentMetricsCollector
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.TeamStore
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class InteractiveComponentBroker constructor(private val slackBlockActionReceivers: List<InteractiveComponentReceiver<BlockActions>>,
                                             private val slackInteractiveMessageReceivers: List<InteractiveComponentReceiver<InteractiveMessage>>,
                                             private val teamStore: TeamStore,
                                             private val metricsCollector: InteractiveComponentMetricsCollector? = null) {

    companion object {
        val LOG = LoggerFactory.getLogger(InteractiveComponentBroker::class.java)
    }

    @PostMapping("/interactive-components", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveEvents(@InteractiveResponse interactiveComponentResponse: InteractiveComponentResponse, @RequestHeader headers: HttpHeaders): ResponseEntity<InteractiveComponentMessageResponse> {
        this.metricsCollector?.responseReceived()
        val team = this.teamStore.findById(interactiveComponentResponse.team.id)
        when (interactiveComponentResponse) {
            is InteractiveMessage -> {
                slackInteractiveMessageReceivers.filter { it.supportsInteractiveMessage(interactiveComponentResponse) }
                        .sortedBy { it.order() }
                        .forEach { receiver ->
                            try {
                                this.metricsCollector?.receiverExecuted()
                                receiver.onReceiveInteractiveMessage(interactiveComponentResponse, headers, team)
                            } catch (e: Exception) {
                                this.metricsCollector?.receiverExecutionError()
                                if (receiver.shouldThrowException(e)) {
                                    throw e
                                }
                            }
                        }
            }
            is BlockActions -> {
                slackBlockActionReceivers.filter { it.supportsInteractiveMessage(interactiveComponentResponse) }
                        .sortedBy { it.order() }
                        .forEach { receiver ->
                            try {
                                this.metricsCollector?.receiverExecuted()
                                receiver.onReceiveInteractiveMessage(interactiveComponentResponse, headers, team)
                            } catch (e: Exception) {
                                this.metricsCollector?.receiverExecutionError()
                                if (receiver.shouldThrowException(e)) {
                                    throw e
                                }
                            }
                        }
            }
        }
        return if (interactiveComponentResponse.type == InteractiveComponentResponse.Type.INTERACTIVE_MESSAGE) {
            ResponseEntity(InteractiveComponentMessageResponse(deleteOriginal = true), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.OK)
        }
    }
}
