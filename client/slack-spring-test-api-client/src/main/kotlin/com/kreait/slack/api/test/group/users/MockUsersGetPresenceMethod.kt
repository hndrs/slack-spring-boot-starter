package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetPresenceRequest
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetPresenceMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersGetPresenceMethod : UsersGetPresenceMethod(), MockMethod<SuccessfulUsersGetPresenceResponse, ErrorUsersGetPresenceResponse, UsersGetPresenceRequest> {

    override fun params() = params

    override var successResponse: SuccessfulUsersGetPresenceResponse? = null
    override var failureResponse: ErrorUsersGetPresenceResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersGetPresenceResponse, ErrorUsersGetPresenceResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
