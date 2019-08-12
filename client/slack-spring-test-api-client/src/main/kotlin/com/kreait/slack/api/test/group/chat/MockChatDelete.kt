package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatDeleteMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse

class MockChatDelete : ChatDeleteMethod(), MockMethod<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, SlackChatDeleteRequest> {

    override fun params(): SlackChatDeleteRequest {
        return params;
    }

    override var successResponse: SuccessfulChatDeleteResponse? = null
    override var failureResponse: ErrorChatDeleteResponse? = null

    override fun request(): ApiCallResult<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
