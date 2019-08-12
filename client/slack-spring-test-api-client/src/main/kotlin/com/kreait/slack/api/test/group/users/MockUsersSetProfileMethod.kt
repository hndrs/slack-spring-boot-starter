package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetProfileMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersSetProfileMethod : UsersSetProfileMethod(), MockMethod<SuccessfulSetProfileResponse, ErrorSetProfileResponse, Unit> {
    override fun params(): Unit {
        return params
    }

    override var successResponse: SuccessfulSetProfileResponse? = null
    override var failureResponse: ErrorSetProfileResponse? = null

    override fun request(): ApiCallResult<SuccessfulSetProfileResponse, ErrorSetProfileResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
