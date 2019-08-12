package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersSetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPresenceResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetPresenceMethod
import com.kreait.slack.api.test.MockMethod

class MockUsersSetPresenceMethod : UsersSetPresenceMethod(), MockMethod<SuccessfulUsersSetPresenceResponse, ErrorUsersSetPresenceResponse, SlackUsersSetPresenceRequest> {

    override fun params() = params

    override var successResponse: SuccessfulUsersSetPresenceResponse? = null
    override var failureResponse: ErrorUsersSetPresenceResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersSetPresenceResponse, ErrorUsersSetPresenceResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
