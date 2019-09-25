package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.groups.GroupsRepliesMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.replies]
 */
open class MockGroupsRepliesMethod : GroupsRepliesMethod(), MockMethod<SuccessfulGroupsRepliesResponse, ErrorGroupsRepliesResponse, GroupsRepliesRequest> {

    override fun params(): GroupsRepliesRequest = params

    override var successResponse: SuccessfulGroupsRepliesResponse? = null
    override var failureResponse: ErrorGroupsRepliesResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsRepliesResponse, ErrorGroupsRepliesResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
