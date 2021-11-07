package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.leave
 */
abstract class ConversationsLeaveMethod :
    ApiCallMethod<ConversationsLeaveMethod, SuccessfulConversationLeaveResponse,
            ErrorConversationLeaveResponse, ConversationsLeaveRequest>()
