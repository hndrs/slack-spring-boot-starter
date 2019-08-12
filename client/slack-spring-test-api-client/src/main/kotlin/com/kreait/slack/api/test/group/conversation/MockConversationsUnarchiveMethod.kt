package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsUnarchiveMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse

/**
 * Mock implementation of @link ConversationsUnarchiveMethod
 */
open class MockConversationsUnarchiveMethod : ConversationsUnarchiveMethod(), MockMethod<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest> {

    override fun params(): ConversationUnarchiveRequest = params

    override var successResponse: SuccessfulConversationUnarchiveResponse? = null
    override var failureResponse: ErrorConversationUnarchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
