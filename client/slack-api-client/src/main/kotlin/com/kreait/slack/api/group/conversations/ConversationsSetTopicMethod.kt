package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse

abstract class ConversationsSetTopicMethod : ApiCallMethod<ConversationsSetTopicMethod, SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse, ConversationsSetTopicRequest>()
