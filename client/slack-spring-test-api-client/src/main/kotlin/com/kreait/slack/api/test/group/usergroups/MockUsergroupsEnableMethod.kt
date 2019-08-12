package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsEnableMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsEnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse

class MockUsergroupsEnableMethod : UsergroupsEnableMethod(), MockMethod<SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse, SlackUsergroupsEnableRequest> {

    override fun params(): SlackUsergroupsEnableRequest = params
    override var successResponse: SuccessfulUsergroupsEnableResponse? = null
    override var failureResponse: ErrorUsergroupsEnableResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
