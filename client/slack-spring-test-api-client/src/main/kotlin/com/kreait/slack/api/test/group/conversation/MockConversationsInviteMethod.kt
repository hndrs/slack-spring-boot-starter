package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsInviteMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.invite]
 */
open class MockConversationsInviteMethod : ConversationsInviteMethod(),
    MockMethod<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse, ConversationsInviteRequest> {
    
    override var successResponse: SuccessfulConversationInviteResponse? = null
    override var failureResponse: ErrorConversationInviteResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsInviteRequest = params
}
