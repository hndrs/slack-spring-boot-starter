package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImCloseMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse

class MockImCloseMethod : ImCloseMethod(), MockMethod<SuccessfulImCloseResponse, ErrorImCloseResponse, SlackImCloseRequest> {

    override fun params() = params

    override var successResponse: SuccessfulImCloseResponse? = null
    override var failureResponse: ErrorImCloseResponse? = null

    override fun request(): ApiCallResult<SuccessfulImCloseResponse, ErrorImCloseResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
