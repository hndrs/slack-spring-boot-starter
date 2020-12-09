package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.leave
 */
abstract class ConversationsLeaveMethod :
    ApiCallMethod<ConversationsLeaveMethod, SuccessfulConversationLeaveResponse,
            ErrorConversationLeaveResponse, ConversationsLeaveRequest>()
