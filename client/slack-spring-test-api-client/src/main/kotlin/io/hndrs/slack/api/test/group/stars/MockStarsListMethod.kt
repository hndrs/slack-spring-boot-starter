package io.hndrs.slack.api.test.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsListRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.stars.StarsListMethod
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [StarsMethodGroup.list]
 */
class MockStarsListMethod : io.hndrs.slack.api.group.stars.StarsListMethod(),
    MockMethod<SuccessfulStarsListResponse, ErrorStarsListResponse, StarsListRequest> {

    override var successResponse: SuccessfulStarsListResponse? = null
    override var failureResponse: ErrorStarsListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulStarsListResponse, ErrorStarsListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
