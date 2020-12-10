package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsCloseMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.close]
 */
open class MockConversationsClose : ConversationsCloseMethod(),
    MockMethod<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse, ConversationCloseRequest> {

    override var successResponse: SuccessfulConversationCloseResponse? = null
    override var failureResponse: ErrorConversationCloseResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationCloseRequest = params
}
