package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.chat.ChatGetPermalinkMethod
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.getPermalink]
 */
class MockChatGetPermalink : io.hndrs.slack.api.group.chat.ChatGetPermalinkMethod(),
    MockMethod<SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse, ChatGetPermalinkRequest> {

    override var successResponse: SuccessfulChatGetPermalinkResponse? = null
    override var failureResponse: ErrorChatGetPermalinkResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ChatGetPermalinkRequest = params
}
