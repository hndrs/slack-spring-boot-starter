package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsJoinMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.join]
 */
class MockConversationsJoinMethod : io.hndrs.slack.api.group.conversations.ConversationsJoinMethod(), MockMethod<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest> {

    override var successResponse: SuccessfulConversationJoinResponse? = null
    override var failureResponse: ErrorConversationJoinResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
