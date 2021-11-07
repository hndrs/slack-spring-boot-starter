package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatUpdateRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Updates a message.
 * https://api.slack.com/methods/chat.update
 */
abstract class ChatUpdateMethod :
    ApiCallMethod<ChatUpdateMethod, SuccessfulChatUpdateResponse, ErrorChatUpdateResponse, ChatUpdateRequest>() {

}
