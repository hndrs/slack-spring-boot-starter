package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ConversationsRequest
import com.kreait.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserConversationsMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.conversations]
 */
class MockUserConversationsMethod : UserConversationsMethod(), MockMethod<SuccessfulConversationsResponse, ErrorConversationsResponse, ConversationsRequest> {

    override fun params(): ConversationsRequest = params

    override var successResponse: SuccessfulConversationsResponse? = null
    override var failureResponse: ErrorConversationsResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationsResponse, ErrorConversationsResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
