package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample

fun SuccessfulConversationJoinResponse.Companion.sample() = SuccessfulConversationJoinResponse(
        true,
        SuccessfulConversationJoinResponse.Channel.sample(),
        "",
        ResponseMetadata.sample()
)

fun ErrorConversationJoinResponse.Companion.sample() = ErrorConversationJoinResponse(false, "")

fun SuccessfulConversationJoinResponse.Channel.Companion.sample() = SuccessfulConversationJoinResponse.Channel("")


fun ConversationJoinRequest.Companion.sample() = ConversationJoinRequest("")

