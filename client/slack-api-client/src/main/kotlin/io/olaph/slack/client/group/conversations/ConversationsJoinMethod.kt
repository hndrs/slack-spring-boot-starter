package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationJoinRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationJoinResponse

abstract class ConversationsJoinMethod : ApiCallMethod<ConversationsJoinMethod, SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest>()