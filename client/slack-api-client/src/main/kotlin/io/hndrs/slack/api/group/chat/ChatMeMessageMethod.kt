package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Share a me message into a channel.
 * https://api.slack.com/methods/chat.meMessage
 */
abstract class ChatMeMessageMethod :
    ApiCallMethod<ChatMeMessageMethod, SuccessfulChatMeMessageResponse,
            ErrorChatMeMessageResponse, ChatMeMessageRequest>() {

}
