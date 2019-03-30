package io.olaph.slack.dto.jackson.group.conversation

import io.olaph.slack.dto.jackson.common.types.Channel
import io.olaph.slack.dto.jackson.group.channels.sample
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCreateResponse

fun ConversationCreateRequest.Companion.sample(): ConversationCreateRequest {
    return ConversationCreateRequest(name = "NewChannelName")
}

fun SuccessfulConversationCreateResponse.Companion.sample(): SuccessfulConversationCreateResponse {
    return SuccessfulConversationCreateResponse(true, Channel.sample())
}

fun ErrorConversationCreateResponse.Companion.sample(): ErrorConversationCreateResponse {
    return ErrorConversationCreateResponse(false, "error")
}
