package io.hndrs.slack.api.test.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetPresenceRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersGetPresenceMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.getPresence]
 */
class MockUsersGetPresenceMethod : io.hndrs.slack.api.group.users.UsersGetPresenceMethod(),
    MockMethod<SuccessfulGetPresenceResponse, ErrorGetPresenceResponse, GetPresenceRequest> {

    override var successResponse: SuccessfulGetPresenceResponse? = null
    override var failureResponse: ErrorGetPresenceResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulGetPresenceResponse, ErrorGetPresenceResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
