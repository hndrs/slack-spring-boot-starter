package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsMembersMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationMembersRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationMembersResponse

open class MockConversationsMembersMethod : ConversationsMembersMethod(), MockMethod<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse, ConversationMembersRequest> {

    override fun params(): ConversationMembersRequest {
        return params;
    }

    override var successResponse: SuccessfulConversationMembersResponse? = null
    override var failureResponse: ErrorConversationMembersResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
