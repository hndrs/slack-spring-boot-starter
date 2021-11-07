package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.setPurpose
 */
abstract class ConversationsSetPurposeMethod :
    ApiCallMethod<ConversationsSetPurposeMethod, SuccessfulConversationSetPurposeResponse,
            ErrorConversationSetPurposeResponse, ConversationsSetPurposeRequest>()
