package com.kreait.slack.api.test.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsAddRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.stars.StarsAddMethod
import com.kreait.slack.api.group.stars.StarsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [StarsMethodGroup.add]
 */
class MockStarsAddMethod : StarsAddMethod(), MockMethod<SuccessfulStarsAddResponse, ErrorStarsAddResponse, StarsAddRequest> {

    override fun params() = params

    override var successResponse: SuccessfulStarsAddResponse? = null
    override var failureResponse: ErrorStarsAddResponse? = null

    override fun request(): ApiCallResult<SuccessfulStarsAddResponse, ErrorStarsAddResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
