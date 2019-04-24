package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsUnarchiveMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationUnarchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationUnarchiveResponse

/**
 * Mock implementation of @link ConversationsUnarchiveMethod
 */
open class MockConversationsUnarchiveMethod : ConversationsUnarchiveMethod(), MockMethod<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest> {

    override fun params(): ConversationUnarchiveRequest = params

    override var successResponse: SuccessfulConversationUnarchiveResponse? = null
    override var failureResponse: ErrorConversationUnarchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
