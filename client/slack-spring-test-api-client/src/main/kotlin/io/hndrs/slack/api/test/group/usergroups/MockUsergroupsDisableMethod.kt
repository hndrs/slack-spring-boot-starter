package io.hndrs.slack.api.test.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.DisableRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsDisableMethod
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.disable]
 */
class MockUsergroupsDisableMethod : io.hndrs.slack.api.group.usergroups.UsergroupsDisableMethod(), MockMethod<SuccessfulDisableResponse, ErrorDisableResponse, DisableRequest> {

    override var successResponse: SuccessfulDisableResponse? = null
    override var failureResponse: ErrorDisableResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulDisableResponse, ErrorDisableResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): DisableRequest = params
}
