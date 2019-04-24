package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRenameRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsRenameResponse

abstract class ConversationsRenameMethod : ApiCallMethod<ConversationsRenameMethod, SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse, ConversationsRenameRequest>() {

}
