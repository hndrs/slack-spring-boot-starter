package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Deletes a message.
 * https://api.slack.com/methods/chat.delete
 */
abstract class ChatDeleteMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.chat.ChatDeleteMethod, SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, ChatDeleteRequest>() {

}
