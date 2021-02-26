package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ConversationsRequest
import io.hndrs.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UserConversationsMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.conversations]
 */
class MockUserConversationsMethod : io.hndrs.slack.api.group.users.UserConversationsMethod(),
    MockMethod<SuccessfulConversationsResponse, ErrorConversationsResponse, ConversationsRequest> {
    
    override var successResponse: SuccessfulConversationsResponse? = null
    override var failureResponse: ErrorConversationsResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationsResponse, ErrorConversationsResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsRequest = params
}
