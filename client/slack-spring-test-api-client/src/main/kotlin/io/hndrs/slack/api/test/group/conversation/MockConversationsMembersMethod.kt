package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMembersMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.members]
 */
open class MockConversationsMembersMethod : io.hndrs.slack.api.group.conversations.ConversationsMembersMethod(),
    MockMethod<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse, ConversationMembersRequest> {

    override var successResponse: SuccessfulConversationMembersResponse? = null
    override var failureResponse: ErrorConversationMembersResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationMembersRequest = params
}
