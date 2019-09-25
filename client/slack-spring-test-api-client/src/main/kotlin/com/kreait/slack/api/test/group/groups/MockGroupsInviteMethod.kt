package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInviteRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInviteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsInviteMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.invite]
 */
open class MockGroupsInviteMethod : GroupsInviteMethod(), MockMethod<SuccessfulGroupsInviteResponse, ErrorGroupsInviteResponse, GroupsInviteRequest> {

    override fun params(): GroupsInviteRequest = params

    override var successResponse: SuccessfulGroupsInviteResponse? = null
    override var failureResponse: ErrorGroupsInviteResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsInviteResponse, ErrorGroupsInviteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
