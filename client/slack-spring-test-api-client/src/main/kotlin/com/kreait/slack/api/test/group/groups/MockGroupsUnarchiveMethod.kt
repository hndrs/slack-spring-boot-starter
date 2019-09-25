package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsUnarchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.groups.GroupsUnarchiveMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.unarchive]
 */
open class MockGroupsUnarchiveMethod : GroupsUnarchiveMethod(), MockMethod<SuccessfulGroupsUnarchiveResponse, ErrorGroupsUnarchiveResponse, GroupsUnarchiveRequest> {

    override fun params(): GroupsUnarchiveRequest = params

    override var successResponse: SuccessfulGroupsUnarchiveResponse? = null
    override var failureResponse: ErrorGroupsUnarchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsUnarchiveResponse, ErrorGroupsUnarchiveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
