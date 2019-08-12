package com.kreait.slack.api.contract.jackson

import com.kreait.slack.api.contract.jackson.SlackEvent

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
