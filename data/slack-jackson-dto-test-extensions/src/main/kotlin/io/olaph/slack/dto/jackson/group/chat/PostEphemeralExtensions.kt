package io.olaph.slack.dto.jackson.group.chat

fun SlackPostEphemeralMessageRequest.Companion.sample(): SlackPostEphemeralMessageRequest {
    return SlackPostEphemeralMessageRequest(
            null,
            listOf(),
            "",
            false,
            null,
            true,
            "none",
            null
    )
}
