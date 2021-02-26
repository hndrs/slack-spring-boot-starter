package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsKickMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.kick]
 */
open class MockConversationsKickMethod : io.hndrs.slack.api.group.conversations.ConversationsKickMethod(), MockMethod<SuccessfulConversationKickResponse, ErrorConversationKickResponse, ConversationsKickRequest> {

    override var successResponse: SuccessfulConversationKickResponse? = null
    override var failureResponse: ErrorConversationKickResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationKickResponse, ErrorConversationKickResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsKickRequest = params
}
