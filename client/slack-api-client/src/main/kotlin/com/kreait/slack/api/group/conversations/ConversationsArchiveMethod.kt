package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse

abstract class ConversationsArchiveMethod : ApiCallMethod<ConversationsArchiveMethod, SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse, ConversationArchiveRequest>()

