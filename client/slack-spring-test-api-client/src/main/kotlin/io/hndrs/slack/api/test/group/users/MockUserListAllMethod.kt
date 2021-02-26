package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorListAllResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListAllRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UserListAllMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.listAll]
 */
class MockUserListAllMethod : io.hndrs.slack.api.group.users.UserListAllMethod(),
    MockMethod<SuccessfulListAllResponse, ErrorListAllResponse, ListAllRequest> {

    override var successResponse: SuccessfulListAllResponse? = null
    override var failureResponse: ErrorListAllResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulListAllResponse, ErrorListAllResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ListAllRequest = params
}
