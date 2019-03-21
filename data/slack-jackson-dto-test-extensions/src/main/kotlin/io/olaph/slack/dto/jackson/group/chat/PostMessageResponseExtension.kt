package io.olaph.slack.dto.jackson.group.chat

fun SlackPostMessageRequest.Companion.sample(): SlackPostMessageRequest {
    return SlackPostMessageRequest(channel = "channelId")
}

fun SuccessfulPostMessageResponse.Companion.sample(): SuccessfulPostMessageResponse = SuccessfulPostMessageResponse(true, "", "")

fun ErrorPostMessageResponse.Companion.sample(): ErrorPostMessageResponse = ErrorPostMessageResponse(false, "")
