package io.olaph.slack.dto.jackson.group.chat

fun SlackPostMessageRequest.Companion.sample(): SlackPostMessageRequest {
    return SlackPostMessageRequest(channel = "channelId")
}
