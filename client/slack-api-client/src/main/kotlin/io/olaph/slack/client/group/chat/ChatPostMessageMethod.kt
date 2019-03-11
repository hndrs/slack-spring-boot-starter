package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostMessageResponse

abstract class ChatPostMessageMethod : ApiCallMethod<ChatPostMessageMethod, SuccessfulPostMessageResponse, ErrorPostMessageResponse, SlackPostMessageRequest>() {

}
