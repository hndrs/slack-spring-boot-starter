package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsMarkRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsMarkMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.mark]
 */
open class MockGroupsMarkMethod : GroupsMarkMethod(), MockMethod<SuccessfulGroupsMarkResponse, ErrorGroupsMarkResponse, GroupsMarkRequest> {

    override fun params(): GroupsMarkRequest = params

    override var successResponse: SuccessfulGroupsMarkResponse? = null
    override var failureResponse: ErrorGroupsMarkResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsMarkResponse, ErrorGroupsMarkResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
