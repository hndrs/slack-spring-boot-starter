package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsOpenRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationOpenResponse

class MockConversationsOpenMethod : ConversationsOpenMethod(), MockMethod<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest> {

    override fun params(): ConversationsOpenRequest = params

    override var successResponse: SuccessfulConversationOpenResponse? = null
    override var failureResponse: ErrorConversationOpenResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}