package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.replies
 */
abstract class ConversationsRepliesMethod : ApiCallMethod<ConversationsRepliesMethod, SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse, ConversationsRepliesRequest>()
