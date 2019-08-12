package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersIdentityResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersIdentityMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersIdentityMethod : UsersIdentityMethod(), MockMethod<SuccessfulUsersIdentityResponse, ErrorUsersIdentityResponse, Unit> {
    override fun params(): Unit {
        return params
    }

    override var successResponse: SuccessfulUsersIdentityResponse? = null
    override var failureResponse: ErrorUsersIdentityResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersIdentityResponse, ErrorUsersIdentityResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
