package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationMembersRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationMembersResponse

abstract class ConversationsMembersMethod : ApiCallMethod<ConversationsMembersMethod, SuccessfulConversationMembersResponse, ErrorConversationMembersResponse, ConversationMembersRequest>() {

}
