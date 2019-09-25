package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsCreateMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ConversationsCreateMethod]
 */
open class MockConversationsCreate : ConversationsCreateMethod(), MockMethod<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse, ConversationCreateRequest> {

    override fun params(): ConversationCreateRequest = params

    override var successResponse: SuccessfulConversationCreateResponse? = null
    override var failureResponse: ErrorConversationCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
