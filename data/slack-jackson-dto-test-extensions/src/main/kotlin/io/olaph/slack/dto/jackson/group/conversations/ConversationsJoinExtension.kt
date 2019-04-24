package io.olaph.slack.dto.jackson.group.conversations

fun SuccessfulConversationJoinResponse.Companion.sample() = SuccessfulConversationJoinResponse(
        true,
        SuccessfulConversationJoinResponse.Channel.sample(),
        "",
        SuccessfulConversationJoinResponse.ResponseMetadata.sample()
)

fun ErrorConversationJoinResponse.Companion.sample() = ErrorConversationJoinResponse(false, "")

fun SuccessfulConversationJoinResponse.Channel.Companion.sample() = SuccessfulConversationJoinResponse.Channel("")

fun SuccessfulConversationJoinResponse.ResponseMetadata.Companion.sample() = SuccessfulConversationJoinResponse.ResponseMetadata(listOf(""))

fun ConversationJoinRequest.Companion.sample() = ConversationJoinRequest("")

