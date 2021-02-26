package io.hndrs.slack.api.test.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.UpdateRequest
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.group.usergroups.UsergroupsUpdateMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.update]
 */
class MockUsergroupsUpdateMethod : io.hndrs.slack.api.group.usergroups.UsergroupsUpdateMethod(), MockMethod<SuccessfulUpdateResponse, ErrorUpdateResponse, UpdateRequest> {

    override var successResponse: SuccessfulUpdateResponse? = null
    override var failureResponse: ErrorUpdateResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulUpdateResponse, ErrorUpdateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): UpdateRequest = params
}
