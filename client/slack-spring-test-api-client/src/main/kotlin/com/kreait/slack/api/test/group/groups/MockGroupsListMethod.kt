package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsListRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsListMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of @link GroupssCloseMethod
 */
open class MockGroupsListMethod : GroupsListMethod(), MockMethod<SuccessfulGroupsListResponse, ErrorGroupsListResponse, GroupsListRequest> {

    override fun params(): GroupsListRequest = params

    override var successResponse: SuccessfulGroupsListResponse? = null
    override var failureResponse: ErrorGroupsListResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsListResponse, ErrorGroupsListResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
