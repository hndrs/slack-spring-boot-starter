package com.kreait.slack.api.contract.jackson

import com.kreait.slack.api.contract.jackson.event.SlackEvent

fun SlackEvent.Companion.sample(): SlackEvent {
    return SlackEvent(
            "",
            "",
            "",
            "",
            setOf(),
            "",
            0,
            mapOf()
    )
}
