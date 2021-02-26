package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import io.hndrs.slack.api.contract.jackson.group.users.LookupByEmailRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersLookupByEmailMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.lookupByEmail]
 */
class MockUsersLookupByEmailMethod : io.hndrs.slack.api.group.users.UsersLookupByEmailMethod(),
    MockMethod<SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse, LookupByEmailRequest> {
    
    override var successResponse: SuccessfulLookupByEmailResponse? = null
    override var failureResponse: ErrorLookupByEmailResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(success = this.successResponse, failure = this.failureResponse)
    }

    override fun params(): LookupByEmailRequest = params
}
