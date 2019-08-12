package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.ImListRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImListMethod
import com.kreait.slack.api.test.MockMethod

class MockImListMethod : ImListMethod(), MockMethod<SuccessfulImListResponse, ErrorImListResponse, ImListRequest> {

    override fun params() = params

    override var successResponse: SuccessfulImListResponse? = null
    override var failureResponse: ErrorImListResponse? = null

    override fun request(): ApiCallResult<SuccessfulImListResponse, ErrorImListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
