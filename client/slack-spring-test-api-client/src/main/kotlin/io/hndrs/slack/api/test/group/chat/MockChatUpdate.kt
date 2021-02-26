package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatUpdateRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatUpdateMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.update]
 */
class MockChatUpdate : io.hndrs.slack.api.group.chat.ChatUpdateMethod(),
    MockMethod<SuccessfulChatUpdateResponse, ErrorChatUpdateResponse, ChatUpdateRequest> {

    override var successResponse: SuccessfulChatUpdateResponse? = null
    override var failureResponse: ErrorChatUpdateResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulChatUpdateResponse, ErrorChatUpdateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatUpdateRequest = params
}
