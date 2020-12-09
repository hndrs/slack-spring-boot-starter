package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.invite
 */
abstract class ConversationsInviteMethod :
    ApiCallMethod<ConversationsInviteMethod, SuccessfulConversationInviteResponse,
            ErrorConversationInviteResponse, ConversationsInviteRequest>()
