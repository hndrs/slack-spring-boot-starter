package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatDeleteMethod
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.delete]
 */
class MockChatDelete : io.hndrs.slack.api.group.chat.ChatDeleteMethod(),
    MockMethod<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, ChatDeleteRequest> {
    
    override var successResponse: SuccessfulChatDeleteResponse? = null
    override var failureResponse: ErrorChatDeleteResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulChatDeleteResponse, ErrorChatDeleteResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatDeleteRequest = params
}
