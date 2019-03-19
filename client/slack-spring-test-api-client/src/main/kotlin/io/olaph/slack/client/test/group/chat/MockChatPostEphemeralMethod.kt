package io.olaph.slack.client.test.group.chat

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatPostEphemeralMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse

open class MockChatPostEphemeralMethod : ChatPostEphemeralMethod(), MockMethod<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, SlackPostEphemeralRequest> {

    override fun params(): SlackPostEphemeralRequest {
        return params;
    }

    override var successResponse: SuccessfulPostEphemeralResponse? = null
    override var failureResponse: ErrorPostEphemeralResponse? = null

    override fun request(): ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
