package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsJoinMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.join]
 */
class MockConversationsJoinMethod : ConversationsJoinMethod(), MockMethod<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest> {

    override var successResponse: SuccessfulConversationJoinResponse? = null
    override var failureResponse: ErrorConversationJoinResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params() = params
}
