package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.kick
 */
abstract class ConversationsKickMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsKickMethod, SuccessfulConversationKickResponse,
            ErrorConversationKickResponse, ConversationsKickRequest>()
