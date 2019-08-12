package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsUpdateMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse

class MockUsergroupsUpdateMethod : UsergroupsUpdateMethod(), MockMethod<SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse, SlackUsergroupsUpdateRequest> {

    override fun params(): SlackUsergroupsUpdateRequest = params
    override var successResponse: SuccessfulUsergroupsUpdateResponse? = null
    override var failureResponse: ErrorUsergroupsUpdateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
