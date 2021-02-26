package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsSetPurposeMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.setPurpose]
 */
class MockConversationsSetPurposeMethod : io.hndrs.slack.api.group.conversations.ConversationsSetPurposeMethod(), MockMethod<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse, ConversationsSetPurposeRequest> {

    override var successResponse: SuccessfulConversationSetPurposeResponse? = null
    override var failureResponse: ErrorConversationSetPurposeResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsSetPurposeRequest = params
}
