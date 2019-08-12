package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse

abstract class ConversationsCloseMethod : ApiCallMethod<ConversationsCloseMethod, SuccessfulConversationCloseResponse, ErrorConversationCloseResponse, ConversationCloseRequest>()
