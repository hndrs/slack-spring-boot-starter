package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsSetTopicMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetTopicRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetTopicResponse

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
