package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationCloseRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse

abstract class ConversationsCloseMethod : ApiCallMethod<ConversationsCloseMethod, SuccessfulConversationCloseResponse, ErrorConversationCloseResponse, ConversationCloseRequest>()
