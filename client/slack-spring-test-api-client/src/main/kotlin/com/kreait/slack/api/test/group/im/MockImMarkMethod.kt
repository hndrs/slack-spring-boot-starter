package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.ImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImMarkMethod
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ImMethodGroup.mark]
 */
class MockImMarkMethod : ImMarkMethod(), MockMethod<SuccessfulImMarkResponse, ErrorImMarkResponse, ImMarkRequest> {

    override fun params(): ImMarkRequest = params

    override var successResponse: SuccessfulImMarkResponse? = null
    override var failureResponse: ErrorImMarkResponse? = null

    override fun request(): ApiCallResult<SuccessfulImMarkResponse, ErrorImMarkResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
