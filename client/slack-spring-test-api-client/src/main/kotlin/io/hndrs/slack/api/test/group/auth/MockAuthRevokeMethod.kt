package io.hndrs.slack.api.test.group.auth

import io.hndrs.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.auth.AuthGroup
import io.hndrs.slack.api.group.auth.AuthRevokeMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [AuthGroup.revoke]
 */
class MockAuthRevokeMethod : io.hndrs.slack.api.group.auth.AuthRevokeMethod(),
    MockMethod<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse, AuthRevokeRequest> {

    override var successResponse: SuccessfulAuthRevokeResponse? = null
    override var failureResponse: ErrorAuthRevokeResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulAuthRevokeResponse, ErrorAuthRevokeResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): AuthRevokeRequest = params
}
