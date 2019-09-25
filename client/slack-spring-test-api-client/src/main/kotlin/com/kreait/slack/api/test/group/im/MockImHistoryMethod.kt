package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.ImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.im.ImHistoryMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ImHistoryMethod]
 */
class MockImHistoryMethod : ImHistoryMethod(), MockMethod<SuccessfulImHistoryResponse, ErrorImHistoryResponse, ImHistoryRequest> {

    override fun params() = params

    override var successResponse: SuccessfulImHistoryResponse? = null
    override var failureResponse: ErrorImHistoryResponse? = null

    override fun request(): ApiCallResult<SuccessfulImHistoryResponse, ErrorImHistoryResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
