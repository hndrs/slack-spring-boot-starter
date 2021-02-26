package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsLeaveMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.leave]
 */
open class MockConversationsLeaveMethod : io.hndrs.slack.api.group.conversations.ConversationsLeaveMethod(),
    MockMethod<SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse, ConversationsLeaveRequest> {

    override var successResponse: SuccessfulConversationLeaveResponse? = null
    override var failureResponse: ErrorConversationLeaveResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsLeaveRequest = params
}
