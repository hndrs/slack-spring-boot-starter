package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsRenameMethod
import com.kreait.slack.api.test.MockMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse

open class MockConversationsRenameMethod : ConversationsRenameMethod(), MockMethod<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse, ConversationsRenameRequest> {

    override fun params(): ConversationsRenameRequest {
        return params;
    }

    override var successResponse: SuccessfulConversationsRenameResponse? = null
    override var failureResponse: ErrorConversationsRenameResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }


}
