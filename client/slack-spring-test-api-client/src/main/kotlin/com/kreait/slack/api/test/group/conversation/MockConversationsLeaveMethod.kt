package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsLeaveMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse

/**
 * Mock implementation of @link ConversationsLeaveMethod
 */
open class MockConversationsLeaveMethod : ConversationsLeaveMethod(), MockMethod<SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse, ConversationsLeaveRequest> {

    override fun params(): ConversationsLeaveRequest = params

    override var successResponse: SuccessfulConversationLeaveResponse? = null
    override var failureResponse: ErrorConversationLeaveResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
