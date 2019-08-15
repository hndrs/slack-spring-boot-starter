package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChatMeMessageMethod : ApiCallMethod<ChatMeMessageMethod, SuccessfulChatMeMessageResponse, ErrorChatMeMessageResponse, ChatMeMessageRequest>() {

}
