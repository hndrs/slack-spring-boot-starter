package io.olaph.slack.client.test.group.auth

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.auth.AuthTestMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthResponse

class MockAuthTestMethod : AuthTestMethod(), MockMethod<SuccessfulAuthResponse, ErrorAuthResponse, Unit> {

    override fun params() {
    }

    override var successResponse: SuccessfulAuthResponse? = null
    override var failureResponse: ErrorAuthResponse? = null

    override fun request(): ApiCallResult<SuccessfulAuthResponse, ErrorAuthResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}

