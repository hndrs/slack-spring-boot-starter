package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetProfileMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetProfileRequest

class MockUsersGetProfileMethod : UsersGetProfileMethod(), MockMethod<SuccessfulUsersGetProfileResponse, ErrorUsersGetProfileResponse, UsersGetProfileRequest> {
    override fun params(): UsersGetProfileRequest {
        return params
    }

    override var successResponse: SuccessfulUsersGetProfileResponse? = null
    override var failureResponse: ErrorUsersGetProfileResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersGetProfileResponse, ErrorUsersGetProfileResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
