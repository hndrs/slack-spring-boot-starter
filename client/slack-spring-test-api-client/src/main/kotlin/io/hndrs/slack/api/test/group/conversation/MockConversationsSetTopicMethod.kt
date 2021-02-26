package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsSetTopicMethod
import io.hndrs.slack.api.test.MockMethod

/**
 * Testable implementation of [ConversationsMethodGroup.setTopic]
 */
class MockConversationsSetTopicMethod : io.hndrs.slack.api.group.conversations.ConversationsSetTopicMethod(),
    MockMethod<SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse, ConversationsSetTopicRequest> {

    override var successResponse: SuccessfulConversationSetTopicResponse? = null
    override var failureResponse: ErrorConversationSetTopicResponse? = null

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return io.hndrs.slack.api.group.ApiCallResult(this.successResponse, this.failureResponse)
    }

    override fun params(): ConversationsSetTopicRequest = params
}
