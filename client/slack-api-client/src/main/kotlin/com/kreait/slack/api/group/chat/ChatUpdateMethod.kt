package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackChatUpdateRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChatUpdateMethod : ApiCallMethod<ChatUpdateMethod, SuccessfulChatUpdateResponse, ErrorChatUpdateResponse, SlackChatUpdateRequest>() {

}
