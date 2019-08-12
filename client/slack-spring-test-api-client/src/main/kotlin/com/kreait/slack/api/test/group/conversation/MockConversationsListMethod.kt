package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsListMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse

open class MockConversationsListMethod : ConversationsListMethod(), MockMethod<SuccessfulConversationListResponse, ErrorConversationListResponse, ConversationsListRequest> {

    override fun params(): ConversationsListRequest {
        return params;
    }

    override var successResponse: SuccessfulConversationListResponse? = null
    override var failureResponse: ErrorConversationListResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationListResponse, ErrorConversationListResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
