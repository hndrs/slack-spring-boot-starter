package io.olaph.slack.client.test.group.respond

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.respond.RespondMessageMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.respond.SlackRespondMessageRequest

class MockRespondMessageMethod : RespondMessageMethod(), MockMethod<Unit, Unit, SlackRespondMessageRequest> {

    override fun params(): SlackRespondMessageRequest {
        return params
    }

    override var successResponse: Unit? = null
    override var failureResponse: Unit? = null

    override fun request(): ApiCallResult<Unit, Unit> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
