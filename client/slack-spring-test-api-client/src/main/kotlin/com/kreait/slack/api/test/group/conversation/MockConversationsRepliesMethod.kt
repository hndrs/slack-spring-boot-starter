package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsRepliesMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ConversationsRepliesMethod]
 */
open class MockConversationsRepliesMethod : ConversationsRepliesMethod(), MockMethod<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse, ConversationsRepliesRequest> {

    override fun params(): ConversationsRepliesRequest = params

    override var successResponse: SuccessfulConversationRepliesResponse? = null
    override var failureResponse: ErrorConversationRepliesResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
