package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersIdentityMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersIdentityResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersIdentityResponse

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
