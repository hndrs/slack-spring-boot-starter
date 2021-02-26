package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.replies
 */
abstract class ConversationsRepliesMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod, SuccessfulConversationRepliesResponse,
            ErrorConversationRepliesResponse, ConversationsRepliesRequest>()
