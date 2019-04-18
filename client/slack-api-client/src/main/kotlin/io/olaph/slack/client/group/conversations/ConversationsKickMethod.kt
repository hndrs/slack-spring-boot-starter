package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsKickRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationKickResponse

abstract class ConversationsKickMethod : ApiCallMethod<ConversationsKickMethod, SuccessfulConversationKickResponse, ErrorConversationKickResponse, ConversationsKickRequest>()