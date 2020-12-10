package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListAllResponse
import com.kreait.slack.api.contract.jackson.group.users.ListAllRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserListAllMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.listAll]
 */
class MockUserListAllMethod : UserListAllMethod(),
    MockMethod<SuccessfulListAllResponse, ErrorListAllResponse, ListAllRequest> {

    override var successResponse: SuccessfulListAllResponse? = null
    override var failureResponse: ErrorListAllResponse? = null

    override fun request(): ApiCallResult<SuccessfulListAllResponse, ErrorListAllResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ListAllRequest = params
}
