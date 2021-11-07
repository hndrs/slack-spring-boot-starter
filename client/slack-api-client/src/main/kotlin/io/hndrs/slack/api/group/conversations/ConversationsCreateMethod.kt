package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.create
 */
abstract class ConversationsCreateMethod :
    ApiCallMethod<ConversationsCreateMethod, SuccessfulConversationCreateResponse,
            ErrorConversationCreateResponse, ConversationCreateRequest>()
