package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.usergroups.UsergroupsListMethod
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.listGroups]
 */
class MockUsergroupsListMethod : UsergroupsListMethod(), MockMethod<SuccessfulListResponse, ErrorListResponse, ListRequest> {

    override fun params(): ListRequest = params

    override var successResponse: SuccessfulListResponse? = null
    override var failureResponse: ErrorListResponse? = null

    override fun request(): ApiCallResult<SuccessfulListResponse, ErrorListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
