package io.hndrs.slack.api.contract.jackson.group.conversations

fun ConversationsSetTopicRequest.Companion.sample() = ConversationsSetTopicRequest("", "")

fun SuccessfulConversationSetTopicResponse.Companion.sample() = SuccessfulConversationSetTopicResponse(true, "")

fun ErrorConversationSetTopicResponse.Companion.sample() = ErrorConversationSetTopicResponse(false, "")
