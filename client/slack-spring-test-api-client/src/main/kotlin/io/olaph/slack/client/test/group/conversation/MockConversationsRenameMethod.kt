package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsRenameMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRenameRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsRenameResponse

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
