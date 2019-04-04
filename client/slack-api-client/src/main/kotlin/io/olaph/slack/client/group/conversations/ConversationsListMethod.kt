package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationListResponse

abstract class ConversationsListMethod : ApiCallMethod<ConversationsListMethod, SuccessfulConversationListResponse, ErrorConversationListResponse, ConversationsListRequest>() {

}
