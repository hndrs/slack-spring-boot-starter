package com.kreait.slack.api.contract.jackson

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
