package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersGetPresenceMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetPresenceRequest

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