package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsOpenMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ConversationsOpenMethod]
 */
class MockConversationsOpenMethod : ConversationsOpenMethod(), MockMethod<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest> {

    override fun params(): ConversationsOpenRequest = params

    override var successResponse: SuccessfulConversationOpenResponse? = null
    override var failureResponse: ErrorConversationOpenResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
