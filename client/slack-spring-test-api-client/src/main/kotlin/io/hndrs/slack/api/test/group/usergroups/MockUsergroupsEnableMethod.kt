package io.hndrs.slack.api.test.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.EnableRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsEnableMethod
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.enable]
 */
class MockUsergroupsEnableMethod : io.hndrs.slack.api.group.usergroups.UsergroupsEnableMethod(), MockMethod<SuccessfulEnableResponse, ErrorEnableResponse, EnableRequest> {

    override var successResponse: SuccessfulEnableResponse? = null
    override var failureResponse: ErrorEnableResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulEnableResponse, ErrorEnableResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): EnableRequest = params
}
