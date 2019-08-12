package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse

abstract class ConversationsListMethod : ApiCallMethod<ConversationsListMethod, SuccessfulConversationListResponse, ErrorConversationListResponse, ConversationsListRequest>() {

}
