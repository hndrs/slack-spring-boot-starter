package io.olaph.slack.client.test.group.auth

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.auth.AuthTestMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthTestResponse

class MockAuthTestMethod : AuthTestMethod(), MockMethod<SuccessfulAuthTestResponse, ErrorAuthTestResponse, Unit> {

    override fun params() {
    }

    override var successResponse: SuccessfulAuthTestResponse? = null
    override var failureResponse: ErrorAuthTestResponse? = null

    override fun request(): ApiCallResult<SuccessfulAuthTestResponse, ErrorAuthTestResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}

