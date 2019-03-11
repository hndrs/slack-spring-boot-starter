package io.olaph.slack.client.test.group.chat

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatPostEphemeralMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralMessageResponse

open class MockChatPostEphemeralMethod : ChatPostEphemeralMethod(), MockMethod<SuccessfulPostEphemeralMessageResponse, ErrorPostEphemeralMessageResponse, SlackPostEphemeralMessageRequest> {

    override fun params(): SlackPostEphemeralMessageRequest {
        return params;
    }

    override var successResponse: SuccessfulPostEphemeralMessageResponse? = null
    override var failureResponse: ErrorPostEphemeralMessageResponse? = null

    override fun request(): ApiCallResult<SuccessfulPostEphemeralMessageResponse, ErrorPostEphemeralMessageResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
