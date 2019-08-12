package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.broker.store.Team
import org.springframework.http.HttpHeaders

interface InteractiveComponentReceiver {

    fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse): Boolean = true

    fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team)
}
