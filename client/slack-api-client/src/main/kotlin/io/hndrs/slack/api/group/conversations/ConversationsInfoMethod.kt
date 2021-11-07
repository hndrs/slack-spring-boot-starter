package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.info
 */
abstract class ConversationsInfoMethod :
    ApiCallMethod<ConversationsInfoMethod, SuccessfulConversationsInfoResponse,
            ErrorConversationsInfoResponse, ConversationsInfoRequest>()
