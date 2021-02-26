package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.invite
 */
abstract class ConversationsInviteMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsInviteMethod, SuccessfulConversationInviteResponse,
            ErrorConversationInviteResponse, ConversationsInviteRequest>()
