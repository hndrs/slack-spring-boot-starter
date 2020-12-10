package com.kreait.slack.api.test.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsAddRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.pins.PinsAddMethod
import com.kreait.slack.api.group.pins.PinsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [PinsMethodGroup.add]
 */
class MockPinsAddMethod : PinsAddMethod(), MockMethod<SuccessfulPinsAddResponse, ErrorPinsAddResponse, PinsAddRequest> {

    override var successResponse: SuccessfulPinsAddResponse? = null
    override var failureResponse: ErrorPinsAddResponse? = null

    override fun request(): ApiCallResult<SuccessfulPinsAddResponse, ErrorPinsAddResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
