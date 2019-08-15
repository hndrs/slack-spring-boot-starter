package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChatUnfurlMethod : ApiCallMethod<ChatUnfurlMethod, SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse, ChatUnfurlRequest>() {

}