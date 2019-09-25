package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatUnfurlMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ChatUnfurlMethod]
 */
class MockChatUnfurl : ChatUnfurlMethod(), MockMethod<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse, ChatUnfurlRequest> {

    override fun params(): ChatUnfurlRequest = params

    override var successResponse: SuccessfulChatUnfurlResponse? = null
    override var failureResponse: ErrorChatUnfurlResponse? = null

    override fun request(): ApiCallResult<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
