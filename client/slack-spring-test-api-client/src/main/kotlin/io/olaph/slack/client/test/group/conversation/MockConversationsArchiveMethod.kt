package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsArchiveMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationArchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationArchiveResponse

/**
 * Mock implementation of @link ConversationsCloseMethod
 */
open class MockConversationsArchiveMethod : ConversationsArchiveMethod(), MockMethod<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse, ConversationArchiveRequest> {

    override fun params(): ConversationArchiveRequest = params

    override var successResponse: SuccessfulConversationArchiveResponse? = null
    override var failureResponse: ErrorConversationArchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
