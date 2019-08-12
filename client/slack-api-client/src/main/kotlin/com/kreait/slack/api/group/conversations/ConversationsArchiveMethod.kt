package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsArchiveMethod : ApiCallMethod<ConversationsArchiveMethod, SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse, ConversationArchiveRequest>()

