package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorGetConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccesfulGetConversationListResponse

abstract class ConversationsListMethod : ApiCallMethod<ConversationsListMethod, SuccesfulGetConversationListResponse, ErrorGetConversationListResponse, ConversationsListRequest>() {

}
