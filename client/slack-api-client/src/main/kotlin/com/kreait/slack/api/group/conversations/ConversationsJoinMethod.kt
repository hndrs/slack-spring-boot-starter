package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsJoinMethod : ApiCallMethod<ConversationsJoinMethod, SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest>()
