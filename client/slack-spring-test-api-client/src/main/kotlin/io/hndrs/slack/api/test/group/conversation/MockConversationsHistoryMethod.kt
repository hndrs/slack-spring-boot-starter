package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsHistoryMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.history]
 */
open class MockConversationsHistoryMethod : io.hndrs.slack.api.group.conversations.ConversationsHistoryMethod(),
    MockMethod<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse, ConversationsHistoryRequest> {
    
    override var successResponse: SuccessfulConversationHistoryResponse? = null
    override var failureResponse: ErrorConversationHistoryResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsHistoryRequest = params
}
