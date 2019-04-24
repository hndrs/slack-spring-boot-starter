package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsListMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationListResponse

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
