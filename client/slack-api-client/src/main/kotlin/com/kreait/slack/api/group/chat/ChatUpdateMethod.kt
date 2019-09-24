package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUpdateRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Updates a message.
 * https://api.slack.com/methods/chat.update
 */
abstract class ChatUpdateMethod : ApiCallMethod<ChatUpdateMethod, SuccessfulChatUpdateResponse, ErrorChatUpdateResponse, ChatUpdateRequest>() {

}
