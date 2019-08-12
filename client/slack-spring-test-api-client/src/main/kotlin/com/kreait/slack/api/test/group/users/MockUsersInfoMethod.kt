package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserInfoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersInfoMethod
import com.kreait.slack.api.test.MockMethod

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
