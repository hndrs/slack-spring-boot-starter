package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.group.users.UsersSetPresenceMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [UsersMethodGroup.setPresence]
 */
class MockUsersSetPresenceMethod : UsersSetPresenceMethod(), MockMethod<SuccessfulSetPresenceResponse, ErrorSetPresenceResponse, SetPresenceRequest> {

    override fun params() = params

    override var successResponse: SuccessfulSetPresenceResponse? = null
    override var failureResponse: ErrorSetPresenceResponse? = null

    override fun request(): ApiCallResult<SuccessfulSetPresenceResponse, ErrorSetPresenceResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
