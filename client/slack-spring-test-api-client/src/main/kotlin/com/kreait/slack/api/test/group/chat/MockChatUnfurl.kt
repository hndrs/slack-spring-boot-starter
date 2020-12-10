package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatUnfurlMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.unfurl]
 */
class MockChatUnfurl : ChatUnfurlMethod(),
    MockMethod<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse, ChatUnfurlRequest> {

    override var successResponse: SuccessfulChatUnfurlResponse? = null
    override var failureResponse: ErrorChatUnfurlResponse? = null

    override fun request(): ApiCallResult<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatUnfurlRequest = params
}
