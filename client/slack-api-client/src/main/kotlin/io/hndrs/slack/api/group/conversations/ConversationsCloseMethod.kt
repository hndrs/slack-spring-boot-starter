package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.close
 */
abstract class ConversationsCloseMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsCloseMethod, SuccessfulConversationCloseResponse,
            ErrorConversationCloseResponse, ConversationCloseRequest>()
