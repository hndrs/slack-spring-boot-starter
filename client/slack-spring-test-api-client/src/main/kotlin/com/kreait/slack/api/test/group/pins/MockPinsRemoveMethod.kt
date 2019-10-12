package com.kreait.slack.api.test.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.pins.PinsRemoveMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Spring based implementation of [PinsMethodGroup.remove]
 */
class MockPinsRemoveMethod : PinsRemoveMethod(), MockMethod<SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse, PinsRemoveRequest> {

    override fun params() = params

    override var successResponse: SuccessfulPinsRemoveResponse? = null
    override var failureResponse: ErrorPinsRemoveResponse? = null

    override fun request(): ApiCallResult<SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
