package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsArchiveMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.archive]
 */
open class MockConversationsArchiveMethod : io.hndrs.slack.api.group.conversations.ConversationsArchiveMethod(),
    MockMethod<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse, ConversationArchiveRequest> {

    override var successResponse: SuccessfulConversationArchiveResponse? = null
    override var failureResponse: ErrorConversationArchiveResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationArchiveRequest = params
}
