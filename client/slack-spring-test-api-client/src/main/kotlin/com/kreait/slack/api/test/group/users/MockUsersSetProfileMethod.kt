package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetProfileMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetProfileResponse

class MockUsersSetProfileMethod : UsersSetProfileMethod(), MockMethod<SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse, Unit> {
    override fun params(): Unit {
        return params
    }

    override var successResponse: SuccessfulUsersSetProfileResponse? = null
    override var failureResponse: ErrorUsersSetProfileResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
