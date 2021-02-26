package io.hndrs.slack.api.test.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsAddRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.stars.StarsAddMethod
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [StarsMethodGroup.add]
 */
class MockStarsAddMethod : io.hndrs.slack.api.group.stars.StarsAddMethod(),
    MockMethod<SuccessfulStarsAddResponse, ErrorStarsAddResponse, StarsAddRequest> {

    override var successResponse: SuccessfulStarsAddResponse? = null
    override var failureResponse: ErrorStarsAddResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulStarsAddResponse, ErrorStarsAddResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
