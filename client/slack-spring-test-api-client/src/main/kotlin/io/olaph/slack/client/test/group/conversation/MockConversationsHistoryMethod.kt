package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsHistoryMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsHistoryRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationHistoryResponse

open class MockConversationsHistoryMethod : ConversationsHistoryMethod(), MockMethod<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse, ConversationsHistoryRequest> {

    override fun params(): ConversationsHistoryRequest {
        return params;
    }

    override var successResponse: SuccessfulConversationHistoryResponse? = null
    override var failureResponse: ErrorConversationHistoryResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
