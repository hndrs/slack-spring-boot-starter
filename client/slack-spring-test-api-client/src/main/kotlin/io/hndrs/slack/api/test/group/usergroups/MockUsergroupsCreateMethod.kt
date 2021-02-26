package io.hndrs.slack.api.test.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.CreateRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.usergroups.UsergroupsCreateMethod
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsergroupsMethodGroup.create]
 */
class MockUsergroupsCreateMethod : io.hndrs.slack.api.group.usergroups.UsergroupsCreateMethod(), MockMethod<SuccessfulCreateResponse, ErrorCreateResponse, CreateRequest> {
    
    override var successResponse: SuccessfulCreateResponse? = null
    override var failureResponse: ErrorCreateResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulCreateResponse, ErrorCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): CreateRequest = params
}
