package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsRenameMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.rename]
 */
open class MockConversationsRenameMethod : io.hndrs.slack.api.group.conversations.ConversationsRenameMethod(), MockMethod<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse, ConversationsRenameRequest> {

    override var successResponse: SuccessfulConversationsRenameResponse? = null
    override var failureResponse: ErrorConversationsRenameResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsRenameRequest = params
}
