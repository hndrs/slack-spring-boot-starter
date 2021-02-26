package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsOpenMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.open]
 */
class MockConversationsOpenMethod : io.hndrs.slack.api.group.conversations.ConversationsOpenMethod(),
    MockMethod<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest> {
    
    override var successResponse: SuccessfulConversationOpenResponse? = null
    override var failureResponse: ErrorConversationOpenResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsOpenRequest = params
}
