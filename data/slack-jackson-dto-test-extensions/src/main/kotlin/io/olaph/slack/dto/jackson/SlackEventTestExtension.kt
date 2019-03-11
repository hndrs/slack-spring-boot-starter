package io.olaph.slack.dto.jackson

import io.olaph.slack.dto.jackson.SlackEvent

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
