package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserConversationsMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserConversationListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserConversationsResponse

class MockUserConversationsMethod : UserConversationsMethod(), MockMethod<SuccessfulUserConversationsResponse, ErrorUserConversationsResponse, SlackUserConversationListRequest> {
    override fun params(): SlackUserConversationListRequest {
        return params
    }

    override var successResponse: SuccessfulUserConversationsResponse? = null
    override var failureResponse: ErrorUserConversationsResponse? = null

    override fun request(): ApiCallResult<SuccessfulUserConversationsResponse, ErrorUserConversationsResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
