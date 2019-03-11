package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImRepliesMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SlackImRepliesRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImRepliesResponse

class MockImRepliesMethod : ImRepliesMethod(), MockMethod<SuccessfulImRepliesResponse, ErrorImRepliesResponse, SlackImRepliesRequest> {
    override var successResponse: SuccessfulImRepliesResponse? = null

    override var failureResponse: ErrorImRepliesResponse? = null

    override fun params(): SlackImRepliesRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulImRepliesResponse, ErrorImRepliesResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
