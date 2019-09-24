package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.open
 */
abstract class ConversationsOpenMethod : ApiCallMethod<ConversationsOpenMethod, SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest>() {

}
