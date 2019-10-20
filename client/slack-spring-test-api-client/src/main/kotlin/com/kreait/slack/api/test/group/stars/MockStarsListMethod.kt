package com.kreait.slack.api.test.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsListRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.stars.StarsListMethod
import com.kreait.slack.api.group.stars.StarsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [StarsMethodGroup.list]
 */
class MockStarsListMethod : StarsListMethod(), MockMethod<SuccessfulStarsListResponse, ErrorStarsListResponse, StarsListRequest> {

    override fun params() = params

    override var successResponse: SuccessfulStarsListResponse? = null
    override var failureResponse: ErrorStarsListResponse? = null

    override fun request(): ApiCallResult<SuccessfulStarsListResponse, ErrorStarsListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
