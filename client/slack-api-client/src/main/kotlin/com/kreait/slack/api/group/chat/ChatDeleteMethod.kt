package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.ChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChatDeleteMethod : ApiCallMethod<ChatDeleteMethod, SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, ChatDeleteRequest>() {

}
