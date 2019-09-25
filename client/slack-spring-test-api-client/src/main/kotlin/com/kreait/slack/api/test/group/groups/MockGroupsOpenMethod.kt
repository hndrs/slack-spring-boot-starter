package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsOpenRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsOpenResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsOpenMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [GroupsOpenMethod]
 */
open class MockGroupsOpenMethod : GroupsOpenMethod(), MockMethod<SuccessfulGroupsOpenResponse, ErrorGroupsOpenResponse, GroupsOpenRequest> {

    override fun params(): GroupsOpenRequest = params

    override var successResponse: SuccessfulGroupsOpenResponse? = null
    override var failureResponse: ErrorGroupsOpenResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsOpenResponse, ErrorGroupsOpenResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
