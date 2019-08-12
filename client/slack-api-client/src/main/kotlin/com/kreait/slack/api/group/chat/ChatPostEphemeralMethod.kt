package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChatPostEphemeralMethod : ApiCallMethod<ChatPostEphemeralMethod, SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse, PostEphemeralRequest>() {

}
