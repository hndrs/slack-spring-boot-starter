package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsCloseMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.close]
 */
open class MockConversationsClose : io.hndrs.slack.api.group.conversations.ConversationsCloseMethod(),
    MockMethod<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse, ConversationCloseRequest> {

    override var successResponse: SuccessfulConversationCloseResponse? = null
    override var failureResponse: ErrorConversationCloseResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationCloseRequest = params
}
