package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.members
 */
abstract class ConversationsMembersMethod : ApiCallMethod<ConversationsMembersMethod, SuccessfulConversationMembersResponse, ErrorConversationMembersResponse, ConversationMembersRequest>() {

}
