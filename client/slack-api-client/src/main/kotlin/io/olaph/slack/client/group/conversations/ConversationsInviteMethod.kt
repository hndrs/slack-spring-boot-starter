package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationInviteResponse

abstract class ConversationsInviteMethod : ApiCallMethod<ConversationsInviteMethod, SuccessfulConversationInviteResponse, ErrorConversationInviteResponse, ConversationsInviteRequest>()
