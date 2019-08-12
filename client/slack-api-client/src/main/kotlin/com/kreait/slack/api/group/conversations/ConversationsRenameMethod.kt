package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse

abstract class ConversationsRenameMethod : ApiCallMethod<ConversationsRenameMethod, SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse, ConversationsRenameRequest>() {

}
