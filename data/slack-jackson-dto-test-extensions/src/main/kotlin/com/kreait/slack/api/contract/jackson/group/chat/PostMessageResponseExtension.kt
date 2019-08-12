package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse

fun SlackPostMessageRequest.Companion.sample(): SlackPostMessageRequest {
    return SlackPostMessageRequest(channel = "channelId", text = "text")
}

fun SuccessfulPostMessageResponse.Companion.sample(): SuccessfulPostMessageResponse = SuccessfulPostMessageResponse(true, "", "")

fun ErrorPostMessageResponse.Companion.sample(): ErrorPostMessageResponse = ErrorPostMessageResponse(false, "")
