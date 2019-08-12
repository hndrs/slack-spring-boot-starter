package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.contract.jackson.group.users.SlackUserListRequest
import com.kreait.slack.api.group.users.UserListMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUserListResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserListResponse

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
