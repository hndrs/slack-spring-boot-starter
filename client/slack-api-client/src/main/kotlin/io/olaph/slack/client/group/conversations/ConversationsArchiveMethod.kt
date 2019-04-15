package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationArchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationArchiveResponse

abstract class ConversationsArchiveMethod : ApiCallMethod<ConversationsArchiveMethod, SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse, ConversationArchiveRequest>()

