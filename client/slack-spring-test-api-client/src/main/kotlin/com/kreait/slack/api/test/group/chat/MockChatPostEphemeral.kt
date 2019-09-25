package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatPostEphemeralMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.postEphemeral]
 */
open class MockChatPostEphemeral : ChatPostEphemeralMethod(), MockMethod<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, PostEphemeralRequest> {

    override fun params(): PostEphemeralRequest = params

    override var successResponse: SuccessfulPostEphemeralResponse? = null
    override var failureResponse: ErrorPostEphemeralResponse? = null

    override fun request(): ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
