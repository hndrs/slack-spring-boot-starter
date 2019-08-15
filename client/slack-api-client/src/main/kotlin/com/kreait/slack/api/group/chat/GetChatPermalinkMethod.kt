package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChatGetPermalinkMethod : ApiCallMethod<ChatGetPermalinkMethod, SuccessfulChatGetPermalinkResponse, ErrorChatGetPermalinkResponse, ChatGetPermalinkRequest>() {

}

