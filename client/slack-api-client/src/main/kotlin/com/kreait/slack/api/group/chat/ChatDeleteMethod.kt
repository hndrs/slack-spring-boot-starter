package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Deletes a message.
 * https://api.slack.com/methods/chat.delete
 */
abstract class ChatDeleteMethod : ApiCallMethod<ChatDeleteMethod, SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, ChatDeleteRequest>() {

}
