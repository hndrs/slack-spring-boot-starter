package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.auth.AuthRevokeMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [AuthRevokeMethod]
 */
class MockAuthRevokeMethod : AuthRevokeMethod(), MockMethod<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest> {

    override fun params(): AuthRevokeRequest = params

    override var successResponse: SuccessfulAuthRevokeResponse? = null
    override var failureResponse: ErrorAuthRevokeResponse? = null

    override fun request(): ApiCallResult<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
