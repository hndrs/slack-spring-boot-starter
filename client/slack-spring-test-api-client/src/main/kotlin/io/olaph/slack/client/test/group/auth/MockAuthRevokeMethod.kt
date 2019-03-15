package io.olaph.slack.client.test.group.auth

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.auth.AuthRevokeMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.auth.AuthRevokeRequest
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthRevokeResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthRevokeResponse

class MockAuthRevokeMethod : AuthRevokeMethod(), MockMethod<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest> {

    override fun params(): AuthRevokeRequest {
        return params
    }

    override var successResponse: SuccessfulAuthRevokeResponse? = null
    override var failureResponse: ErrorAuthRevokeResponse? = null

    override fun request(): ApiCallResult<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
