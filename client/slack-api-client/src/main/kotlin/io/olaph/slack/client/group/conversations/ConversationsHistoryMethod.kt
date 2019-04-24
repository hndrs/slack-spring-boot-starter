package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsHistoryRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationHistoryResponse

abstract class ConversationsHistoryMethod : ApiCallMethod<ConversationsHistoryMethod, SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse, ConversationsHistoryRequest>()