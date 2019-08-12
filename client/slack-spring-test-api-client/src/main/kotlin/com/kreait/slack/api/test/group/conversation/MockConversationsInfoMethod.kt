package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsInfoMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse

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
