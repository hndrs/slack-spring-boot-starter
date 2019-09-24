package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.unarchive
 */
abstract class ConversationsUnarchiveMethod : ApiCallMethod<ConversationsUnarchiveMethod, SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse, ConversationUnarchiveRequest>()
