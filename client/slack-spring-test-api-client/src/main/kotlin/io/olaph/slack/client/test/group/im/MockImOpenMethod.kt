package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImOpenMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImOpenResponse
import io.olaph.slack.dto.jackson.group.im.SlackImOpenRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImOpenResponse

class MockImOpenMethod : ImOpenMethod(), MockMethod<SuccessfulImOpenResponse, ErrorImOpenResponse, SlackImOpenRequest> {
    override var successResponse: SuccessfulImOpenResponse? = null

    override var failureResponse: ErrorImOpenResponse? = null

    override fun params(): SlackImOpenRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulImOpenResponse, ErrorImOpenResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
