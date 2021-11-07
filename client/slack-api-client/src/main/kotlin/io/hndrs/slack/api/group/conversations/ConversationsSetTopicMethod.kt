package io.hndrs.slack.api.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/conversations.setTopic
 */
abstract class ConversationsSetTopicMethod :
    ApiCallMethod<ConversationsSetTopicMethod, SuccessfulConversationSetTopicResponse,
            ErrorConversationSetTopicResponse, ConversationsSetTopicRequest>()
