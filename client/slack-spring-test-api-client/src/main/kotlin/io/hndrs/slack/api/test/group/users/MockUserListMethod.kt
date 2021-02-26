package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UserListMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.list]
 */
class MockUserListMethod : io.hndrs.slack.api.group.users.UserListMethod(), MockMethod<SuccessfulListResponse, ErrorListResponse, ListRequest> {
    
    override var successResponse: SuccessfulListResponse? = null
    override var failureResponse: ErrorListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulListResponse, ErrorListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ListRequest = params
}
