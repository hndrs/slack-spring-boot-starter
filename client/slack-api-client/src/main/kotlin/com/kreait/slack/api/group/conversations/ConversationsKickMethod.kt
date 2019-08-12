package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse

abstract class ConversationsKickMethod : ApiCallMethod<ConversationsKickMethod, SuccessfulConversationKickResponse, ErrorConversationKickResponse, ConversationsKickRequest>()
