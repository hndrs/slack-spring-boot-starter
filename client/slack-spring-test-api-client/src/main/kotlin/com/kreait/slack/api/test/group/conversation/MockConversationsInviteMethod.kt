package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsInviteMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse

/**
 * Mock implementation of @link ConversationsInviteMethod
 */
open class MockConversationsInviteMethod : ConversationsInviteMethod(), MockMethod<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse, ConversationsInviteRequest> {

    override fun params(): ConversationsInviteRequest = params

    override var successResponse: SuccessfulConversationInviteResponse? = null
    override var failureResponse: ErrorConversationInviteResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
