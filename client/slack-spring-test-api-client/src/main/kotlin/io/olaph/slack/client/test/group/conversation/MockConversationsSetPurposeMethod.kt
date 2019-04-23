package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsSetPurposeMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetPurposeRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetPurposeResponse

class MockConversationsSetPurposeMethod : ConversationsSetPurposeMethod(), MockMethod<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse, ConversationsSetPurposeRequest> {

    override fun params(): ConversationsSetPurposeRequest = params

    override var successResponse: SuccessfulConversationSetPurposeResponse? = null
    override var failureResponse: ErrorConversationSetPurposeResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}