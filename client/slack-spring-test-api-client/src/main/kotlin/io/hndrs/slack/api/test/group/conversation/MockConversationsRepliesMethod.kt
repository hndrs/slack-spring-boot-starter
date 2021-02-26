package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.replies]
 */
open class MockConversationsRepliesMethod : io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod(),
    MockMethod<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse, ConversationsRepliesRequest> {

    override var successResponse: SuccessfulConversationRepliesResponse? = null
    override var failureResponse: ErrorConversationRepliesResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsRepliesRequest = params
}
