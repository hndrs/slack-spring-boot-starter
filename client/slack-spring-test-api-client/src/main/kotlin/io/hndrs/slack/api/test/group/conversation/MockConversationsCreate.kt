package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsCreateMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.create]
 */
open class MockConversationsCreate : io.hndrs.slack.api.group.conversations.ConversationsCreateMethod(),
    MockMethod<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse, ConversationCreateRequest> {

    override var successResponse: SuccessfulConversationCreateResponse? = null
    override var failureResponse: ErrorConversationCreateResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
