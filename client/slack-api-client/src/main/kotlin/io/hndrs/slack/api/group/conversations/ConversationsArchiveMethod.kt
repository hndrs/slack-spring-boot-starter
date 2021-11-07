package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.archive
 */
abstract class ConversationsArchiveMethod :
    ApiCallMethod<ConversationsArchiveMethod, SuccessfulConversationArchiveResponse,
            ErrorConversationArchiveResponse, ConversationArchiveRequest>()

