package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsLeaveMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsLeaveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationLeaveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationLeaveResponse

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
