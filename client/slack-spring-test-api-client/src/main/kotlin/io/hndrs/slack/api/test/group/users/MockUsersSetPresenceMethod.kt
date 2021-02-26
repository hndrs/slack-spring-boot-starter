package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetPresenceRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetPresenceMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.setPresence]
 */
class MockUsersSetPresenceMethod : io.hndrs.slack.api.group.users.UsersSetPresenceMethod(),
    MockMethod<SuccessfulSetPresenceResponse, ErrorSetPresenceResponse, SetPresenceRequest> {

    override var successResponse: SuccessfulSetPresenceResponse? = null
    override var failureResponse: ErrorSetPresenceResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulSetPresenceResponse, ErrorSetPresenceResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
