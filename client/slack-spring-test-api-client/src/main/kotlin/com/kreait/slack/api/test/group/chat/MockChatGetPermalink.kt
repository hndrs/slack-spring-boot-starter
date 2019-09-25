package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatGetPermalinkMethod
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChatMethodGroup.getPermalink]
 */
class MockChatGetPermalink : ChatGetPermalinkMethod(), MockMethod<SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse, ChatGetPermalinkRequest> {

    override fun params(): ChatGetPermalinkRequest = params

    override var successResponse: SuccessfulChatGetPermalinkResponse? = null
    override var failureResponse: ErrorChatGetPermalinkResponse? = null

    override fun request(): ApiCallResult<SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
