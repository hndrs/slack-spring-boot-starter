package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsDisableMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsDisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse

class MockUsergroupsDisableMethod : UsergroupsDisableMethod(), MockMethod<SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse, SlackUsergroupsDisableRequest> {

    override fun params(): SlackUsergroupsDisableRequest = params

    override var successResponse: SuccessfulUsergroupsDisableResponse? = null
    override var failureResponse: ErrorUsergroupsDisableResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
