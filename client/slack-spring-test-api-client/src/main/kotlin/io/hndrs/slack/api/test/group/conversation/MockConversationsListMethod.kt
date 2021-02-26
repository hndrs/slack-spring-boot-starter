package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsListMethod
import io.hndrs.slack.api.test.MockMethod

import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup

/**
 * Testable implementation of [ConversationsMethodGroup.archive]
 */
open class MockConversationsListMethod : io.hndrs.slack.api.group.conversations.ConversationsListMethod(), MockMethod<SuccessfulConversationListResponse, ErrorConversationListResponse, ConversationsListRequest> {

    override var successResponse: SuccessfulConversationListResponse? = null
    override var failureResponse: ErrorConversationListResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationListResponse, ErrorConversationListResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsListRequest = params
}
