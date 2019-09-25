package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsJoinMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ConversationsJoinMethod]
 */
class MockConversationsJoinMethod : ConversationsJoinMethod(), MockMethod<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest> {

    override fun params(): ConversationJoinRequest = params

    override var successResponse: SuccessfulConversationJoinResponse? = null
    override var failureResponse: ErrorConversationJoinResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
