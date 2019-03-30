package io.olaph.slack.client.test.group.chat

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatPostMessageMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostMessageResponse

open class MockChatPostMessage : ChatPostMessageMethod(), MockMethod<SuccessfulPostMessageResponse, ErrorPostMessageResponse, SlackPostMessageRequest> {

    override fun params(): SlackPostMessageRequest {
        return params;
    }

    override var successResponse: SuccessfulPostMessageResponse? = null
    override var failureResponse: ErrorPostMessageResponse? = null

    override fun request(): ApiCallResult<SuccessfulPostMessageResponse, ErrorPostMessageResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
