package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsCloseMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationCloseRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse

/**
* Mock implementation of @link ConversationsCloseMethod
*/
open class MockConversationsClose : ConversationsCloseMethod(), MockMethod<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse, ConversationCloseRequest> {

    override fun params(): ConversationCloseRequest = params

    override var successResponse: SuccessfulConversationCloseResponse? = null
    override var failureResponse: ErrorConversationCloseResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
