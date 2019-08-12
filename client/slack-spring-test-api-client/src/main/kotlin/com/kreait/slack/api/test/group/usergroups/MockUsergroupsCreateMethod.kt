package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsCreateMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse

class MockUsergroupsCreateMethod : UsergroupsCreateMethod(), MockMethod<SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse, SlackUsergroupsCreateRequest> {

    override fun params(): SlackUsergroupsCreateRequest = params

    override var successResponse: SuccessfulUsergroupsCreateResponse? = null
    override var failureResponse: ErrorUsergroupsCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
