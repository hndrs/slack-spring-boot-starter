package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsUnarchiveMethod : ApiCallMethod<ConversationsUnarchiveMethod, SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest>()
