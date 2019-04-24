package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsRepliesMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRepliesRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationRepliesResponse

/**
 * Mock implementation of @link ConversationsLeaveMethod
 */
open class MockConversationsRepliesMethod : ConversationsRepliesMethod(), MockMethod<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse, ConversationsRepliesRequest> {

    override fun params(): ConversationsRepliesRequest = params

    override var successResponse: SuccessfulConversationRepliesResponse? = null
    override var failureResponse: ErrorConversationRepliesResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
