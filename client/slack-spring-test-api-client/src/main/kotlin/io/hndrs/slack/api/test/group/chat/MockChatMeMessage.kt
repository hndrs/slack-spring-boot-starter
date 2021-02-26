package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMeMessageMethod
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.meMessage]
 */
class MockChatMeMessage : io.hndrs.slack.api.group.chat.ChatMeMessageMethod(),
    MockMethod<SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse, ChatMeMessageRequest> {

    override var successResponse: SuccessfulChatMeMessageResponse? = null
    override var failureResponse: ErrorChatMeMessageResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatMeMessageRequest = params
}
