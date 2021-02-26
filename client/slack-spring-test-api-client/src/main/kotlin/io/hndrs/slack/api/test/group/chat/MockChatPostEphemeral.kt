package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.chat.ChatPostEphemeralMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.postEphemeral]
 */
open class MockChatPostEphemeral : io.hndrs.slack.api.group.chat.ChatPostEphemeralMethod(),
    MockMethod<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, PostEphemeralRequest> {


    override var successResponse: SuccessfulPostEphemeralResponse? = null
    override var failureResponse: ErrorPostEphemeralResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): PostEphemeralRequest = params
}
