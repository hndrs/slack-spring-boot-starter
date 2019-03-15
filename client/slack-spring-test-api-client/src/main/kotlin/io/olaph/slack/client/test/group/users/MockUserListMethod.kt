package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.SlackUserListRequest
import io.olaph.slack.client.group.users.UserListMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse

class MockUserListMethod : UserListMethod(), MockMethod<SuccessfulUserListResponse, ErrorUserListResponse, SlackUserListRequest> {
    override fun params(): SlackUserListRequest {
        return params
    }

    override var successResponse: SuccessfulUserListResponse? = null
    override var failureResponse: ErrorUserListResponse? = null

    override fun request(): ApiCallResult<SuccessfulUserListResponse, ErrorUserListResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}