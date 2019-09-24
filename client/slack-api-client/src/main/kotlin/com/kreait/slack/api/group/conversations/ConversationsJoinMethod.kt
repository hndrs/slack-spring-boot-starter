package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.join
 */
abstract class ConversationsJoinMethod : ApiCallMethod<ConversationsJoinMethod, SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest>()
