package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersInfoMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserInfoRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersInfoResponse

class MockUsersInfoMethod : UsersInfoMethod(), MockMethod<SuccessfulUsersInfoResponse, ErrorUsersInfoResponse, SlackUserInfoRequest> {
    override fun params(): SlackUserInfoRequest {
        return params
    }

    override var successResponse: SuccessfulUsersInfoResponse? = null
    override var failureResponse: ErrorUsersInfoResponse? = null

    override fun request(): ApiCallResult<SuccessfulUsersInfoResponse, ErrorUsersInfoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
