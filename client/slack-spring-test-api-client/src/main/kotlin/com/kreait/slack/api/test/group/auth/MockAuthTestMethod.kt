package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.auth.AuthTestMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [AuthGroup.test]
 */
class MockAuthTestMethod : AuthTestMethod(), MockMethod<SuccessfulAuthTestResponse, ErrorAuthTestResponse, Unit> {

    override fun params() {
        // since this method has not params the body is empty
    }

    override var successResponse: SuccessfulAuthTestResponse? = null
    override var failureResponse: ErrorAuthTestResponse? = null

    override fun request(): ApiCallResult<SuccessfulAuthTestResponse, ErrorAuthTestResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}

