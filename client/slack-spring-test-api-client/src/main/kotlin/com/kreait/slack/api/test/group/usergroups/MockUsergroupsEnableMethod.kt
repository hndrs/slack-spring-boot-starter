package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.EnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsEnableMethod
import com.kreait.slack.api.test.MockMethod

class MockUsergroupsEnableMethod : UsergroupsEnableMethod(), MockMethod<SuccessfulEnableResponse, ErrorEnableResponse, EnableRequest> {

    override fun params(): EnableRequest = params
    override var successResponse: SuccessfulEnableResponse? = null
    override var failureResponse: ErrorEnableResponse? = null

    override fun request(): ApiCallResult<SuccessfulEnableResponse, ErrorEnableResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
