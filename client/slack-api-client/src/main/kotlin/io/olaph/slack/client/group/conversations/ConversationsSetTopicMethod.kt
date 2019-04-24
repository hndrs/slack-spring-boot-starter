package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetPurposeRequest
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetTopicRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetTopicResponse

abstract class ConversationsSetTopicMethod : ApiCallMethod<ConversationsSetTopicMethod, SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse, ConversationsSetTopicRequest>()
