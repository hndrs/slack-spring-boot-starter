package io.olaph.slack.client.test.group.chat

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatDeleteMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatDeleteRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatDeleteResponse

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
