package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInfoRequest
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsInfoResponse

abstract class ConversationsInfoMethod : ApiCallMethod<ConversationsInfoMethod, SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse, ConversationsInfoRequest>()
