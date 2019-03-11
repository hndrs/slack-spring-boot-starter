package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackDeleteRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulDeleteResponse

abstract class ChatDeleteMethod : ApiCallMethod<ChatDeleteMethod, SuccessfulDeleteResponse, ErrorDeleteResponse, SlackDeleteRequest>() {

}
