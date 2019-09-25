package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetTopicResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsSetTopicMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [GroupsSetTopicMethod]
 */
open class MockGroupsSetTopicMethod : GroupsSetTopicMethod(), MockMethod<SuccessfulGroupsSetTopicResponse, ErrorGroupsSetTopicResponse, GroupsSetTopicRequest> {

    override fun params(): GroupsSetTopicRequest = params

    override var successResponse: SuccessfulGroupsSetTopicResponse? = null
    override var failureResponse: ErrorGroupsSetTopicResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsSetTopicResponse, ErrorGroupsSetTopicResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
