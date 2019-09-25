package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.ImOpenRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImOpenMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ImOpenMethod]
 */
class MockImOpenMethod : ImOpenMethod(), MockMethod<SuccessfulImOpenResponse, ErrorImOpenResponse, ImOpenRequest> {

    override fun params(): ImOpenRequest = params

    override var successResponse: SuccessfulImOpenResponse? = null
    override var failureResponse: ErrorImOpenResponse? = null

    override fun request(): ApiCallResult<SuccessfulImOpenResponse, ErrorImOpenResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
