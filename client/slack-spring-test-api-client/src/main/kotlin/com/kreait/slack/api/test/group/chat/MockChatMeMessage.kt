package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatMeMessageMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ChatMeMessageMethod]
 */
class MockChatMeMessage : ChatMeMessageMethod(), MockMethod<SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse, ChatMeMessageRequest> {

    override fun params(): ChatMeMessageRequest = params

    override var successResponse: SuccessfulChatMeMessageResponse? = null
    override var failureResponse: ErrorChatMeMessageResponse? = null

    override fun request(): ApiCallResult<SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
