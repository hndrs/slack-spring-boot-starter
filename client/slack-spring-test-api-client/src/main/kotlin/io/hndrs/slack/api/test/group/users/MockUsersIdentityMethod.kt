package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersIdentityMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.identity]
 */
class MockUsersIdentityMethod : io.hndrs.slack.api.group.users.UsersIdentityMethod(),
    MockMethod<SuccessfulIdentityResponse, ErrorIdentityResponse, Unit> {

    override var successResponse: SuccessfulIdentityResponse? = null
    override var failureResponse: ErrorIdentityResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulIdentityResponse, ErrorIdentityResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() {
        // This method has no params thus this body is empty
    }
}
