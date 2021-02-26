package io.hndrs.slack.api.test.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsListRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.pins.PinsListMethod
import io.hndrs.slack.api.group.pins.PinsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [PinsMethodGroup.list]
 */
class MockPinsListMethod : io.hndrs.slack.api.group.pins.PinsListMethod(),
    MockMethod<SuccessfulPinsListResponse, ErrorPinsListResponse, PinsListRequest> {

    override var successResponse: SuccessfulPinsListResponse? = null
    override var failureResponse: ErrorPinsListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulPinsListResponse, ErrorPinsListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
