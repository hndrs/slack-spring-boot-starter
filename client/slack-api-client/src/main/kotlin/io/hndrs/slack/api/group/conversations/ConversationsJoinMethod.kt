package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.join
 */
abstract class ConversationsJoinMethod :
    ApiCallMethod<ConversationsJoinMethod, SuccessfulConversationJoinResponse,
            ErrorConversationJoinResponse, ConversationJoinRequest>()
