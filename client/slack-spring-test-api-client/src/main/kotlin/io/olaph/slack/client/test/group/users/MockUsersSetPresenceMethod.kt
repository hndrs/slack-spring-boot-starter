package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersSetPresenceMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersSetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPresenceResponse

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