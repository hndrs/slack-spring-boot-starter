package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.DisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsDisableMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [UsergroupsDisableMethod]
 */
class MockUsergroupsDisableMethod : UsergroupsDisableMethod(), MockMethod<SuccessfulDisableResponse, ErrorDisableResponse, DisableRequest> {

    override fun params(): DisableRequest = params

    override var successResponse: SuccessfulDisableResponse? = null
    override var failureResponse: ErrorDisableResponse? = null

    override fun request(): ApiCallResult<SuccessfulDisableResponse, ErrorDisableResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
