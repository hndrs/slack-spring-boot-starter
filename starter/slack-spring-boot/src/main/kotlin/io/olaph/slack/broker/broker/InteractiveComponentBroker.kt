package io.olaph.slack.broker.broker

import io.olaph.slack.broker.configuration.InteractiveResponse
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.dto.jackson.group.dialog.InteractiveComponentResponse
import io.olaph.slack.dto.jackson.group.interactive_component.InteractiveComponentMessageResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class InteractiveComponentBroker constructor(private val slackInteractiveComponentReceivers: List<InteractiveComponentReceiver>,
                                             private val teamStore: TeamStore) {

    @PostMapping("/interactive-components", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveEvents(@InteractiveResponse interactiveComponentResponse: InteractiveComponentResponse, @RequestHeader headers: HttpHeaders): ResponseEntity<InteractiveComponentMessageResponse> {
        val team = this.teamStore.findById(interactiveComponentResponse.team.id)
        slackInteractiveComponentReceivers
                .filter { it -> it.supportsInteractiveMessage(interactiveComponentResponse) }
                .forEach { receiver ->
                    receiver.onReceiveInteractiveMessage(interactiveComponentResponse, headers, team)
                }
        return if (interactiveComponentResponse.type == "interactive_message") {
            ResponseEntity(InteractiveComponentMessageResponse(deleteOriginal = true), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.OK)
        }
    }
}
