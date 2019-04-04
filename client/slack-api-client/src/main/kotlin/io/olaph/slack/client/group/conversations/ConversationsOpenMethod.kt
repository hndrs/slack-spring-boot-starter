package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsOpenRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationOpenResponse

abstract class ConversationsOpenMethod : ApiCallMethod<ConversationsOpenMethod, SuccessfulConversationOpenResponse, ErrorConversationOpenResponse, ConversationsOpenRequest>() {

}
