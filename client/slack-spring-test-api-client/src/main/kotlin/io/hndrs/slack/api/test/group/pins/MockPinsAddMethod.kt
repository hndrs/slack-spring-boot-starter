package io.hndrs.slack.api.test.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsAddRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.pins.PinsAddMethod
import io.hndrs.slack.api.group.pins.PinsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [PinsMethodGroup.add]
 */
class MockPinsAddMethod : io.hndrs.slack.api.group.pins.PinsAddMethod(), MockMethod<SuccessfulPinsAddResponse, ErrorPinsAddResponse, PinsAddRequest> {

    override var successResponse: SuccessfulPinsAddResponse? = null
    override var failureResponse: ErrorPinsAddResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulPinsAddResponse, ErrorPinsAddResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
