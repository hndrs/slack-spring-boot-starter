package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorChatUpdateResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatUpdateRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatUpdateResponse

abstract class ChatUpdateMethod : ApiCallMethod<ChatUpdateMethod, SuccessfulChatUpdateResponse, ErrorChatUpdateResponse, SlackChatUpdateRequest>() {

}
