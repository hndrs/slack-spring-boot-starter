package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralMessageResponse

abstract class ChatPostEphemeralMethod : ApiCallMethod<ChatPostEphemeralMethod, SuccessfulPostEphemeralMessageResponse, ErrorPostEphemeralMessageResponse, SlackPostEphemeralMessageRequest>() {

}
