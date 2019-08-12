package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse

fun ConversationsSetTopicRequest.Companion.sample() = ConversationsSetTopicRequest("", "")

fun SuccessfulConversationSetTopicResponse.Companion.sample() = SuccessfulConversationSetTopicResponse(true, "")

fun ErrorConversationSetTopicResponse.Companion.sample() = ErrorConversationSetTopicResponse(false, "")
