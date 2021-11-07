package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.members
 */
abstract class ConversationsMembersMethod :
    ApiCallMethod<ConversationsMembersMethod, SuccessfulConversationMembersResponse,
            ErrorConversationMembersResponse, ConversationMembersRequest>()
