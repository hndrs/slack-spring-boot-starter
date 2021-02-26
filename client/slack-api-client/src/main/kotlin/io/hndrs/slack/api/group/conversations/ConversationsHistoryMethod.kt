package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.history
 */
abstract class ConversationsHistoryMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsHistoryMethod, SuccessfulConversationHistoryResponse,
            ErrorConversationHistoryResponse, ConversationsHistoryRequest>()
