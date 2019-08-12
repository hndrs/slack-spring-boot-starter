package com.kreait.slack.api.group.chat

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse

abstract class ChatPostEphemeralMethod : ApiCallMethod<ChatPostEphemeralMethod, SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, SlackPostEphemeralRequest>() {

}
