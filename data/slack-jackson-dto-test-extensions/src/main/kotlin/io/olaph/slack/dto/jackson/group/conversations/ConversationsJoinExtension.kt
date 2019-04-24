package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.sample

fun SuccessfulConversationJoinResponse.Companion.sample() = SuccessfulConversationJoinResponse(
        true,
        SuccessfulConversationJoinResponse.Channel.sample(),
        "",
        ResponseMetadata.sample()
)

fun ErrorConversationJoinResponse.Companion.sample() = ErrorConversationJoinResponse(false, "")

fun SuccessfulConversationJoinResponse.Channel.Companion.sample() = SuccessfulConversationJoinResponse.Channel("")


fun ConversationJoinRequest.Companion.sample() = ConversationJoinRequest("")

