package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse

abstract class ConversationsUnarchiveMethod : ApiCallMethod<ConversationsUnarchiveMethod, SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest>()
