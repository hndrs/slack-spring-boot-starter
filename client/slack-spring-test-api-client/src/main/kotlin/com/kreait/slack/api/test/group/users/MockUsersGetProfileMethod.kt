package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetProfileRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetProfileMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersGetProfileMethod : UsersGetProfileMethod(), MockMethod<SuccessfulGetProfileResponse, ErrorGetProfileResponse, GetProfileRequest> {
    override fun params(): GetProfileRequest {
        return params
    }

    override var successResponse: SuccessfulGetProfileResponse? = null
    override var failureResponse: ErrorGetProfileResponse? = null

    override fun request(): ApiCallResult<SuccessfulGetProfileResponse, ErrorGetProfileResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
