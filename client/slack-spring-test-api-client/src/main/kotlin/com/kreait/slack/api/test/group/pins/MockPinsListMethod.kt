package com.kreait.slack.api.test.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsListRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.pins.PinsListMethod
import com.kreait.slack.api.group.pins.PinsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [PinsMethodGroup.list]
 */
class MockPinsListMethod : PinsListMethod(), MockMethod<SuccessfulPinsListResponse, ErrorPinsListResponse, PinsListRequest> {

    override fun params() = params

    override var successResponse: SuccessfulPinsListResponse? = null
    override var failureResponse: ErrorPinsListResponse? = null

    override fun request(): ApiCallResult<SuccessfulPinsListResponse, ErrorPinsListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
