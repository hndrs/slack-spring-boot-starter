package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCreateResponse

abstract class ConversationsCreateMethod : ApiCallMethod<ConversationsCreateMethod, SuccessfulConversationCreateResponse, ErrorConversationCreateResponse, ConversationCreateRequest>()
