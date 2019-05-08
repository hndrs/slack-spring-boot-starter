package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImHistoryMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.SlackImHistoryRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImHistoryResponse

class MockImHistoryMethod : ImHistoryMethod(), MockMethod<SuccessfulImHistoryResponse, ErrorImHistoryResponse, SlackImHistoryRequest> {

    override fun params() = params

    override var successResponse: SuccessfulImHistoryResponse? = null
    override var failureResponse: ErrorImHistoryResponse? = null

    override fun request(): ApiCallResult<SuccessfulImHistoryResponse, ErrorImHistoryResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}