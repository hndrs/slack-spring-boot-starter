package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsArchiveMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of @link GroupssCloseMethod
 */
open class MockGroupsArchiveMethod : GroupsArchiveMethod(), MockMethod<SuccessfulGroupsArchiveResponse, ErrorGroupsArchiveResponse, GroupsArchiveRequest> {

    override fun params(): GroupsArchiveRequest = params

    override var successResponse: SuccessfulGroupsArchiveResponse? = null
    override var failureResponse: ErrorGroupsArchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsArchiveResponse, ErrorGroupsArchiveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
