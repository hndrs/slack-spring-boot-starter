package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsListMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsListResponse

class MockUsergroupsListMethod : UsergroupsListMethod(), MockMethod<SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse, SlackUsergroupsListRequest> {

    override fun params(): SlackUsergroupsListRequest = params

    override var successResponse: SuccessfulUsergroupsListResponse? = null
    override var failureResponse: ErrorUsergroupsListResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
