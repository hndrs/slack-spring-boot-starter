package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sends an ephemeral message to a user in a channel.
 * https://api.slack.com/methods/chat.postEphemeral
 */
abstract class ChatPostEphemeralMethod :
    ApiCallMethod<ChatPostEphemeralMethod, SuccessfulPostEphemeralResponse,
            ErrorPostEphemeralResponse, PostEphemeralRequest>() {

}
