package io.hndrs.slack.api.test.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ListRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsListMethod
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.listGroups]
 */
class MockUsergroupsListMethod : io.hndrs.slack.api.group.usergroups.UsergroupsListMethod(), MockMethod<SuccessfulListResponse, ErrorListResponse, ListRequest> {

    override var successResponse: SuccessfulListResponse? = null
    override var failureResponse: ErrorListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulListResponse, ErrorListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ListRequest = params
}
