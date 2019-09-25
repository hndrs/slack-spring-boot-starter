package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsSetPurposeMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [GroupsSetPurposeMethod]
 */
open class MockGroupsSetPurposeMethod : GroupsSetPurposeMethod(), MockMethod<SuccessfulGroupsSetPurposeResponse, ErrorGroupsSetPurposeResponse, GroupsSetPurposeRequest> {

    override fun params(): GroupsSetPurposeRequest = params

    override var successResponse: SuccessfulGroupsSetPurposeResponse? = null
    override var failureResponse: ErrorGroupsSetPurposeResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsSetPurposeResponse, ErrorGroupsSetPurposeResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
