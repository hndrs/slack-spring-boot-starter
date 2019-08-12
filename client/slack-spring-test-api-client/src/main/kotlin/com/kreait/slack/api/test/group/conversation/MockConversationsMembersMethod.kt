package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsMembersMethod
import com.kreait.slack.api.test.MockMethod

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
