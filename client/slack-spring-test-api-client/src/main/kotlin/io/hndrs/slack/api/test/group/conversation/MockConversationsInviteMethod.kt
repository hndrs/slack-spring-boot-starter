package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsInviteMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.invite]
 */
open class MockConversationsInviteMethod : io.hndrs.slack.api.group.conversations.ConversationsInviteMethod(),
    MockMethod<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse, ConversationsInviteRequest> {
    
    override var successResponse: SuccessfulConversationInviteResponse? = null
    override var failureResponse: ErrorConversationInviteResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsInviteRequest = params
}
