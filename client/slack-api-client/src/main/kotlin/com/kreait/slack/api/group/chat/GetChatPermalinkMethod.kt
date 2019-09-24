package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Retrieve a permalink URL for a specific extant message
 * https://api.slack.com/methods/chat.getPermalink
 */
abstract class ChatGetPermalinkMethod : ApiCallMethod<ChatGetPermalinkMethod, SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse, ChatGetPermalinkRequest>() {

}

