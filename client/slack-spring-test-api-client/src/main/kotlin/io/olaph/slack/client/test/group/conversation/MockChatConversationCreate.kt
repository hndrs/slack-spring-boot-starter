package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsCreateMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCreateResponse

/**
 * Mock implementation of @link ConversationsCreateMethod
 */
open class MockChatConversationCreate : ConversationsCreateMethod(), MockMethod<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse, ConversationCreateRequest> {

    override fun params(): ConversationCreateRequest {
        return params;
    }

    override var successResponse: SuccessfulConversationCreateResponse? = null
    override var failureResponse: ErrorConversationCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
