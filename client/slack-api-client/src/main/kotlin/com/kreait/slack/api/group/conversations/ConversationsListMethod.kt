package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsListMethod : ApiCallMethod<ConversationsListMethod, SuccessfulConversationListResponse, ErrorConversationListResponse, ConversationsListRequest>() {

}
