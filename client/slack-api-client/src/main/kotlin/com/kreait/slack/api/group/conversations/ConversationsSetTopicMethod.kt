package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.setTopic
 */
abstract class ConversationsSetTopicMethod : ApiCallMethod<ConversationsSetTopicMethod, SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse, ConversationsSetTopicRequest>()
