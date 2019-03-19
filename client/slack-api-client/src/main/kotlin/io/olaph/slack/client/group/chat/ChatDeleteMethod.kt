package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatDeleteRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatDeleteResponse

abstract class ChatDeleteMethod : ApiCallMethod<ChatDeleteMethod, SuccessfulChatDeleteResponse, ErrorChatDeleteResponse, SlackChatDeleteRequest>() {

}
