package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationUnarchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationUnarchiveResponse

abstract class ConversationsUnarchiveMethod : ApiCallMethod<ConversationsUnarchiveMethod, SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest>()
