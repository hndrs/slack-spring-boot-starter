package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse

abstract class ConversationsOpenMethod : ApiCallMethod<ConversationsOpenMethod, SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest>() {

}
