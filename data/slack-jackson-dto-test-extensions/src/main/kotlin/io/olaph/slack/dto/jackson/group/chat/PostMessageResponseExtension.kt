package io.olaph.slack.dto.jackson.group.chat

fun SlackPostMessageRequest.Companion.sample(): SlackPostMessageRequest {
    return SlackPostMessageRequest(
            null,
            listOf(),
            "",
            false,
            null,
            "none",
            null
    )
}
