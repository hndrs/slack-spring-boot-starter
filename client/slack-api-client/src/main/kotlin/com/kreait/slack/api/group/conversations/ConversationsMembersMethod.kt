package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse

abstract class ConversationsMembersMethod : ApiCallMethod<ConversationsMembersMethod, SuccessfulConversationMembersResponse, ErrorConversationMembersResponse, ConversationMembersRequest>() {

}
