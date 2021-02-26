package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.open
 */
abstract class ConversationsOpenMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsOpenMethod, SuccessfulConversationOpenResponse,
            ErrorConversationOpenResponse, ConversationsOpenRequest>()
