package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRepliesRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationRepliesResponse

abstract class ConversationsRepliesMethod : ApiCallMethod<ConversationsRepliesMethod, SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse, ConversationsRepliesRequest>()
