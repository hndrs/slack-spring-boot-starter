package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsKickMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsKickRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationKickResponse

open class MockConversationsKickMethod : ConversationsKickMethod(), MockMethod<SuccessfulConversationKickResponse, ErrorConversationKickResponse, ConversationsKickRequest> {

    override fun params(): ConversationsKickRequest = params
    override var successResponse: SuccessfulConversationKickResponse? = null
    override var failureResponse: ErrorConversationKickResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationKickResponse, ErrorConversationKickResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}