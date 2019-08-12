package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse

abstract class ConversationsRepliesMethod : ApiCallMethod<ConversationsRepliesMethod, SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse, ConversationsRepliesRequest>()
