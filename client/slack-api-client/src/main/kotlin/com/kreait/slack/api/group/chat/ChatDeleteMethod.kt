package com.kreait.slack.api.group.chat

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse

abstract class ChatDeleteMethod : ApiCallMethod<ChatDeleteMethod, SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, SlackChatDeleteRequest>() {

}
