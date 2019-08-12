package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsHistoryMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse

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
