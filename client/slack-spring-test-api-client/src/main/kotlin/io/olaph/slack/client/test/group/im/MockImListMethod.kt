package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImListMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImListResponse
import io.olaph.slack.dto.jackson.group.im.SlackImListRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImListResponse

class MockImListMethod : ImListMethod(), MockMethod<SuccessfulImListResponse, ErrorImListResponse, SlackImListRequest> {

    override fun params() = params

    override var successResponse: SuccessfulImListResponse? = null
    override var failureResponse: ErrorImListResponse? = null

    override fun request(): ApiCallResult<SuccessfulImListResponse, ErrorImListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
