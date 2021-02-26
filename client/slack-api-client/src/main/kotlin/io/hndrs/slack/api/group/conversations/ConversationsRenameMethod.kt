package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.rename
 */
abstract class ConversationsRenameMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsRenameMethod, SuccessfulConversationsRenameResponse,
            ErrorConversationsRenameResponse, ConversationsRenameRequest>()
