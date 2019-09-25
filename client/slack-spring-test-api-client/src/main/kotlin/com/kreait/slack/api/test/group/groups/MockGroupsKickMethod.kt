package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsKickRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsKickResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsKickMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.kick]
 */
open class MockGroupsKickMethod : GroupsKickMethod(), MockMethod<SuccessfulGroupsKickResponse, ErrorGroupsKickResponse, GroupsKickRequest> {

    override fun params(): GroupsKickRequest = params

    override var successResponse: SuccessfulGroupsKickResponse? = null
    override var failureResponse: ErrorGroupsKickResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsKickResponse, ErrorGroupsKickResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
