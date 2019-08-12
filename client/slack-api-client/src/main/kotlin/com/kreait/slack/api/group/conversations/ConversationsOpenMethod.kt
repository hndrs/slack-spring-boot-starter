package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsOpenMethod : ApiCallMethod<ConversationsOpenMethod, SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest>() {

}
