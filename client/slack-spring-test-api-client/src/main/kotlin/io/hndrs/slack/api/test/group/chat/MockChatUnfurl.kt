package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.chat.ChatUnfurlMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.unfurl]
 */
class MockChatUnfurl : io.hndrs.slack.api.group.chat.ChatUnfurlMethod(),
    MockMethod<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse, ChatUnfurlRequest> {

    override var successResponse: SuccessfulChatUnfurlResponse? = null
    override var failureResponse: ErrorChatUnfurlResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatUnfurlRequest = params
}
