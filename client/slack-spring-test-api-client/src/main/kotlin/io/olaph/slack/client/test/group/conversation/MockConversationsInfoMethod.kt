package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsInfoMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInfoRequest
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsInfoResponse

open class MockConversationsInfoMethod : ConversationsInfoMethod(), MockMethod<SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse, ConversationsInfoRequest> {

    override fun params(): ConversationsInfoRequest {
        return params;
    }

    override var successResponse: SuccessfulConversationsInfoResponse? = null
    override var failureResponse: ErrorConversationsInfoResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
