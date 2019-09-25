package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsSetPurposeMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.setPurpose]
 */
class MockConversationsSetPurposeMethod : ConversationsSetPurposeMethod(), MockMethod<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse, ConversationsSetPurposeRequest> {

    override fun params(): ConversationsSetPurposeRequest = params

    override var successResponse: SuccessfulConversationSetPurposeResponse? = null
    override var failureResponse: ErrorConversationSetPurposeResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
