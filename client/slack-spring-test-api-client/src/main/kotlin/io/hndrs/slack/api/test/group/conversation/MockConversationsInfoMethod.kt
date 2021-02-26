package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsInfoMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.info]
 */
open class MockConversationsInfoMethod : io.hndrs.slack.api.group.conversations.ConversationsInfoMethod(), MockMethod<SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse, ConversationsInfoRequest> {

    override var successResponse: SuccessfulConversationsInfoResponse? = null
    override var failureResponse: ErrorConversationsInfoResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsInfoRequest = params
}
