package com.kreait.slack.api.test.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsRemoveRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.stars.StarsMethodGroup
import com.kreait.slack.api.group.stars.StarsRemoveMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [StarsMethodGroup.remove]
 */
class MockStarsRemoveMethod : StarsRemoveMethod(), MockMethod<SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse, StarsRemoveRequest> {

    override fun params() = params

    override var successResponse: SuccessfulStarsRemoveResponse? = null
    override var failureResponse: ErrorStarsRemoveResponse? = null

    override fun request(): ApiCallResult<SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
