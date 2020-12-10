package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsKickMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.kick]
 */
open class MockConversationsKickMethod : ConversationsKickMethod(), MockMethod<SuccessfulConversationKickResponse, ErrorConversationKickResponse, ConversationsKickRequest> {

    override var successResponse: SuccessfulConversationKickResponse? = null
    override var failureResponse: ErrorConversationKickResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationKickResponse, ErrorConversationKickResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsKickRequest = params
}
