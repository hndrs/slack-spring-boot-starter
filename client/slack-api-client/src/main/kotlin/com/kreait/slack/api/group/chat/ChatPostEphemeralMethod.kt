package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sends an ephemeral message to a user in a channel.
 * https://api.slack.com/methods/chat.postEphemeral
 */
abstract class ChatPostEphemeralMethod :
    ApiCallMethod<ChatPostEphemeralMethod, SuccessfulPostEphemeralResponse,
            ErrorPostEphemeralResponse, PostEphemeralRequest>() {

}
