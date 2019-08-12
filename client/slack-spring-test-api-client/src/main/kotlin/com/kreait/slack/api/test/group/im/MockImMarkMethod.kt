package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImMarkMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse

class MockImMarkMethod : ImMarkMethod(), MockMethod<SuccessfulImMarkResponse, ErrorImMarkResponse, SlackImMarkRequest> {
    override var successResponse: SuccessfulImMarkResponse? = null

    override var failureResponse: ErrorImMarkResponse? = null

    override fun params(): SlackImMarkRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulImMarkResponse, ErrorImMarkResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
