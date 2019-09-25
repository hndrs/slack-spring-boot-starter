package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsHistoryMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.history]
 */
open class MockGroupsHistoryMethod : GroupsHistoryMethod(), MockMethod<SuccessfulGroupsHistoryResponse, ErrorGroupsHistoryResponse, GroupsHistoryRequest> {

    override fun params(): GroupsHistoryRequest = params

    override var successResponse: SuccessfulGroupsHistoryResponse? = null
    override var failureResponse: ErrorGroupsHistoryResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsHistoryResponse, ErrorGroupsHistoryResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
