package com.kreait.slack.broker.broker

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.group.interactive_component.InteractiveComponentMessageResponse
import com.kreait.slack.broker.configuration.InteractiveResponse
import com.kreait.slack.broker.exception.ExceptionChain
import com.kreait.slack.broker.exception.MustThrow
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

        exceptionChain.evaluate()

        return if (interactiveComponentResponse.type == "interactive_message") {
            ResponseEntity(InteractiveComponentMessageResponse(deleteOriginal = true), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.OK)
        }
    }
}
