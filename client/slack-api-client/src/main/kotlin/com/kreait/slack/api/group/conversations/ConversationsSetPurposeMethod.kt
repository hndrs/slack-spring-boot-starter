package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsSetPurposeMethod : ApiCallMethod<ConversationsSetPurposeMethod, SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse, ConversationsSetPurposeRequest>()
