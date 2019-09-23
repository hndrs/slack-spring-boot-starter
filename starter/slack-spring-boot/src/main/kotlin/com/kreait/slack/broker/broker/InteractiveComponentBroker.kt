package com.kreait.slack.broker.broker

import com.kreait.slack.api.contract.jackson.BlockActions
import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.InteractiveMessage
import com.kreait.slack.api.contract.jackson.group.interactive_component.InteractiveComponentMessageResponse
import com.kreait.slack.broker.configuration.InteractiveResponse
import com.kreait.slack.broker.metrics.InteractiveComponentMetricsCollector
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.team.Team
import com.kreait.slack.broker.store.team.TeamStore
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

/**
 * Component broker that forwards all [InteractiveComponentResponse]s to all [InteractiveComponentReceiver]s
 */
@RestController
class InteractiveComponentBroker constructor(private val slackBlockActionReceivers: List<InteractiveComponentReceiver<BlockActions>>,
                                             private val slackInteractiveMessageReceivers: List<InteractiveComponentReceiver<InteractiveMessage>>,
                                             private val teamStore: TeamStore,
                                             private val metricsCollector: InteractiveComponentMetricsCollector? = null) {

    companion object {
        val LOG = LoggerFactory.getLogger(InteractiveComponentBroker::class.java)
    }

    /**
     * Endpoint that receives the components
     */
    @PostMapping("/interactive-components", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveComponent(@InteractiveResponse interactiveComponentResponse: InteractiveComponentResponse, @RequestHeader headers: HttpHeaders): ResponseEntity<InteractiveComponentMessageResponse> {
        this.metricsCollector?.responseReceived()
        val team = this.teamStore.findById(interactiveComponentResponse.team.id)
        when (interactiveComponentResponse) {
            is InteractiveMessage -> {
                invokeInteractiveMessages(interactiveComponentResponse, headers, team)
            }
            is BlockActions -> {
                invokeBlockMessages(interactiveComponentResponse, headers, team)
            }
        }
        return if (interactiveComponentResponse.type == InteractiveComponentResponse.Type.INTERACTIVE_MESSAGE) {
            ResponseEntity(InteractiveComponentMessageResponse(deleteOriginal = true), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.OK)
        }
    }

    private fun invokeBlockMessages(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
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

    private fun invokeInteractiveMessages(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
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
}
