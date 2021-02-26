package io.hndrs.slack.api.test.group.auth

import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.auth.AuthGroup
import io.hndrs.slack.api.group.auth.AuthTestMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [AuthGroup.test]
 */
class MockAuthTestMethod : io.hndrs.slack.api.group.auth.AuthTestMethod(), MockMethod<SuccessfulAuthTestResponse, ErrorAuthTestResponse, Unit> {

    override var successResponse: SuccessfulAuthTestResponse? = null
    override var failureResponse: ErrorAuthTestResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulAuthTestResponse, ErrorAuthTestResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() {
        // This method has no params thus this body is empty
    }
}
