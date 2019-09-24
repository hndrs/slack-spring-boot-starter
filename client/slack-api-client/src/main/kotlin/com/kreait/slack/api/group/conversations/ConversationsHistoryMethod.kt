package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.history
 */
abstract class ConversationsHistoryMethod : ApiCallMethod<ConversationsHistoryMethod, SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse, ConversationsHistoryRequest>()
