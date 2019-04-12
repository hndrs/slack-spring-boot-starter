package io.olaph.slack.broker.receiver

import io.olaph.slack.broker.store.Team
import io.olaph.slack.dto.jackson.InteractiveComponentResponse
import org.springframework.http.HttpHeaders

interface InteractiveComponentReceiver {

    fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse): Boolean = true

    fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team)
}
