package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsSetTopicMethod
import com.kreait.slack.api.test.MockMethod

class MockConversationsSetTopicMethod : ConversationsSetTopicMethod(), MockMethod<SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse, ConversationsSetTopicRequest> {

    override fun params(): ConversationsSetTopicRequest = params

    override var successResponse: SuccessfulConversationSetTopicResponse? = null
    override var failureResponse: ErrorConversationSetTopicResponse? = null

    override fun request(): ApiCallResult<SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
