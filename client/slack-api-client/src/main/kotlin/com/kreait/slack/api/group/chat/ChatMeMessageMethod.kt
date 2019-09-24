package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Share a me message into a channel.
 * https://api.slack.com/methods/chat.meMessage
 */
abstract class ChatMeMessageMethod : ApiCallMethod<ChatMeMessageMethod, SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse, ChatMeMessageRequest>() {

}
