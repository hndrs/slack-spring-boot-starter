package io.hndrs.slack.api.test.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.pins.PinsMethodGroup
import io.hndrs.slack.api.group.pins.PinsRemoveMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [PinsMethodGroup.remove]
 */
class MockPinsRemoveMethod : io.hndrs.slack.api.group.pins.PinsRemoveMethod(),
    MockMethod<SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse, PinsRemoveRequest> {

    override var successResponse: SuccessfulPinsRemoveResponse? = null
    override var failureResponse: ErrorPinsRemoveResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
