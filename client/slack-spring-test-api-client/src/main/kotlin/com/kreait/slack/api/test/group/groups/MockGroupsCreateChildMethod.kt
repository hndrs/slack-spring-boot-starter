package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsCreateChildMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [GroupsCreateChildMethod]
 */
open class MockGroupsCreateChildMethod : GroupsCreateChildMethod(), MockMethod<SuccessfulGroupsCreateChildResponse, ErrorGroupsCreateChildResponse, GroupsCreateChildRequest> {

    override fun params(): GroupsCreateChildRequest = params

    override var successResponse: SuccessfulGroupsCreateChildResponse? = null
    override var failureResponse: ErrorGroupsCreateChildResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsCreateChildResponse, ErrorGroupsCreateChildResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
