package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsJoinMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationJoinRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationJoinResponse

class MockConversationsJoinMethod : ConversationsJoinMethod(), MockMethod<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse, ConversationJoinRequest>{

    override fun params() = params

    override var successResponse: SuccessfulConversationJoinResponse? = null
    override var failureResponse: ErrorConversationJoinResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}