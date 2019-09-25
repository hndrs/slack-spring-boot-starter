package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsLeaveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsLeaveMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [GroupsLeaveMethod]
 */
open class MockGroupsLeaveMethod : GroupsLeaveMethod(), MockMethod<SuccessfulGroupsLeaveResponse, ErrorGroupsLeaveResponse, GroupsLeaveRequest> {

    override fun params(): GroupsLeaveRequest = params

    override var successResponse: SuccessfulGroupsLeaveResponse? = null
    override var failureResponse: ErrorGroupsLeaveResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsLeaveResponse, ErrorGroupsLeaveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
