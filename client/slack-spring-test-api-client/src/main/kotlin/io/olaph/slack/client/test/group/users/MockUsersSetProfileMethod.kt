package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersSetProfileMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetProfileResponse

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
