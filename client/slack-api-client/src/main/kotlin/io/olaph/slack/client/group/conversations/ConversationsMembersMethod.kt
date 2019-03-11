package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorGetMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulGetMembersResponse

abstract class ConversationsMembersMethod : ApiCallMethod<ConversationsMembersMethod, SuccessfulGetMembersResponse, ErrorGetMembersResponse, MutableMap<String, String>>() {

}
