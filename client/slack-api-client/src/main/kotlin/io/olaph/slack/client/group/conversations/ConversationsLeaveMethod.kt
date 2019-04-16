package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsLeaveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationLeaveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationLeaveResponse

abstract class ConversationsLeaveMethod : ApiCallMethod<ConversationsLeaveRequest, SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse, ConversationsLeaveRequest>()
