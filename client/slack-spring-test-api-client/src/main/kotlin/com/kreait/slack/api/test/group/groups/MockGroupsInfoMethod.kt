package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInfoRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsInfoMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.info]
 */
open class MockGroupsInfoMethod : GroupsInfoMethod(), MockMethod<SuccessfulGroupsInfoResponse, ErrorGroupsInfoResponse, GroupsInfoRequest> {

    override fun params(): GroupsInfoRequest = params

    override var successResponse: SuccessfulGroupsInfoResponse? = null
    override var failureResponse: ErrorGroupsInfoResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsInfoResponse, ErrorGroupsInfoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
