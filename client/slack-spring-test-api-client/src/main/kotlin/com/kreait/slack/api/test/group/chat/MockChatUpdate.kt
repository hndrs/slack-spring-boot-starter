package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUpdateRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatUpdateMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.update]
 */
class MockChatUpdate : ChatUpdateMethod(),
    MockMethod<SuccessfulChatUpdateResponse, ErrorChatUpdateResponse, ChatUpdateRequest> {

    override var successResponse: SuccessfulChatUpdateResponse? = null
    override var failureResponse: ErrorChatUpdateResponse? = null

    override fun request(): ApiCallResult<SuccessfulChatUpdateResponse, ErrorChatUpdateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatUpdateRequest = params
}
