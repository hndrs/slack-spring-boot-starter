package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetPresenceMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.getPresence]
 */
class MockUsersGetPresenceMethod : UsersGetPresenceMethod(), MockMethod<SuccessfulGetPresenceResponse, ErrorGetPresenceResponse, GetPresenceRequest> {

    override fun params() = params

    override var successResponse: SuccessfulGetPresenceResponse? = null
    override var failureResponse: ErrorGetPresenceResponse? = null

    override fun request(): ApiCallResult<SuccessfulGetPresenceResponse, ErrorGetPresenceResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
