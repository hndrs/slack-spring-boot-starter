package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersGetProfileMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetProfileRequest

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
