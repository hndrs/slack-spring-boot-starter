package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImMarkMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse

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
