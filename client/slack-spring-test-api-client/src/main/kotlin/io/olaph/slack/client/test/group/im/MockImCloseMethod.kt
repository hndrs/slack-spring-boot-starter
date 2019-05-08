package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImCloseMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImCloseResponse
import io.olaph.slack.dto.jackson.group.im.SlackImCloseRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImCloseResponse

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