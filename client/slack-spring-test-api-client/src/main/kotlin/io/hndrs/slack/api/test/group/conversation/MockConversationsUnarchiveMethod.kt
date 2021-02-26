package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsUnarchiveMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.unarchive]
 */
open class MockConversationsUnarchiveMethod : io.hndrs.slack.api.group.conversations.ConversationsUnarchiveMethod(),
    MockMethod<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest> {
    
    override var successResponse: SuccessfulConversationUnarchiveResponse? = null
    override var failureResponse: ErrorConversationUnarchiveResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationUnarchiveRequest = params
}
