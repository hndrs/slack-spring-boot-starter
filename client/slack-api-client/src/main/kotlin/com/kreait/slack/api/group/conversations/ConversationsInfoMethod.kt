package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.info
 */
abstract class ConversationsInfoMethod : ApiCallMethod<ConversationsInfoMethod, SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse, ConversationsInfoRequest>()
