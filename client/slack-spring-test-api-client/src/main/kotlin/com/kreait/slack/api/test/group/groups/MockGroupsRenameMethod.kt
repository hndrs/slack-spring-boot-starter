package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRenameRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRenameResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.groups.GroupsRenameMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [GroupsMethodGroup.rename]
 */
open class MockGroupsRenameMethod : GroupsRenameMethod(), MockMethod<SuccessfulGroupsRenameResponse, ErrorGroupsRenameResponse, GroupsRenameRequest> {

    override fun params(): GroupsRenameRequest = params

    override var successResponse: SuccessfulGroupsRenameResponse? = null
    override var failureResponse: ErrorGroupsRenameResponse? = null

    override fun request(): ApiCallResult<SuccessfulGroupsRenameResponse, ErrorGroupsRenameResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
