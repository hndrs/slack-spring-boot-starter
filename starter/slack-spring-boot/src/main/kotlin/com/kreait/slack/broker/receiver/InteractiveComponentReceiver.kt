package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.broker.store.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

interface InteractiveComponentReceiver {

    fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse): Boolean = true

    fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team)

    fun shouldThrowException(exception:Exception): Boolean = false

    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}
