package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsCreateMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.create]
 */
open class MockGroupsCreateMethod : GroupsCreateMethod(), MockMethod<SuccessfulGroupsCreateResponse, ErrorGroupsCreateResponse, GroupsCreateRequest> {

    override fun params(): GroupsCreateRequest = params

    override var successResponse: SuccessfulGroupsCreateResponse? = null
    override var failureResponse: ErrorGroupsCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsCreateResponse, ErrorGroupsCreateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
