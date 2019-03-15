package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UserConversationsMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserConversationListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse

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