package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.broker.store.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

interface InteractiveComponentReceiver<Type :InteractiveComponentResponse> {

    fun supportsInteractiveMessage(interactiveComponentResponse: Type): Boolean = true

    fun onReceiveInteractiveMessage(interactiveComponentResponse: Type, headers: HttpHeaders, team: Team)

    fun shouldThrowException(exception: Exception): Boolean = false

    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}
