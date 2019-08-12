package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsLeaveMethod : ApiCallMethod<ConversationsLeaveMethod, SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse, ConversationsLeaveRequest>()
