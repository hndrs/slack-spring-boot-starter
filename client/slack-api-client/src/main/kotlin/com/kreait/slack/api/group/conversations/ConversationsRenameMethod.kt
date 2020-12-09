package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.rename
 */
abstract class ConversationsRenameMethod :
    ApiCallMethod<ConversationsRenameMethod, SuccessfulConversationsRenameResponse,
            ErrorConversationsRenameResponse, ConversationsRenameRequest>()
