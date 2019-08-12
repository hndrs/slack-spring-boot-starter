package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatPostEphemeralMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse

open class MockChatPostEphemeral : ChatPostEphemeralMethod(), MockMethod<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, SlackPostEphemeralRequest> {

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
