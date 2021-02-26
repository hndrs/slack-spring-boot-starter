package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Retrieve a permalink URL for a specific extant message
 * https://api.slack.com/methods/chat.getPermalink
 */
abstract class ChatGetPermalinkMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.chat.ChatGetPermalinkMethod, SuccessfulChatGetPermalinkResponse,
            ErrorChatGetPermalinkResponse, ChatGetPermalinkRequest>() {

}

