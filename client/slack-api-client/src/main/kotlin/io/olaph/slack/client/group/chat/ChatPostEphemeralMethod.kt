package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse

abstract class ChatPostEphemeralMethod : ApiCallMethod<ChatPostEphemeralMethod, SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, SlackPostEphemeralRequest>() {

}
