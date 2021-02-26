package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.unarchive
 */
abstract class ConversationsUnarchiveMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsUnarchiveMethod, SuccessfulConversationUnarchiveResponse,
            ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest>()
