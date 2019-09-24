package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.archive
 */
abstract class ConversationsArchiveMethod : ApiCallMethod<ConversationsArchiveMethod, SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse, ConversationArchiveRequest>()

