package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsInviteMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationInviteResponse

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
