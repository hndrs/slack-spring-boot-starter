package io.hndrs.slack.api.test.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.group.stars.StarsRemoveMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [StarsMethodGroup.remove]
 */
class MockStarsRemoveMethod : io.hndrs.slack.api.group.stars.StarsRemoveMethod(),
    MockMethod<SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse, StarsRemoveRequest> {

    override var successResponse: SuccessfulStarsRemoveResponse? = null
    override var failureResponse: ErrorStarsRemoveResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
