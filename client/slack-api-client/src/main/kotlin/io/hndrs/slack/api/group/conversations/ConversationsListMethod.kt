package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.list
 */
abstract class ConversationsListMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsListMethod, SuccessfulConversationListResponse,
            ErrorConversationListResponse, ConversationsListRequest>()
