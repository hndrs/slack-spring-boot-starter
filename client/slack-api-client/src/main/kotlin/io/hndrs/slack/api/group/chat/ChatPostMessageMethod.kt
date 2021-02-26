package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import io.hndrs.slack.api.contract.jackson.group.chat.PostMessageRequest
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sends a message to a user in a channel.
 * https://api.slack.com/methods/chat.postMessage
 */
abstract class ChatPostMessageMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.chat.ChatPostMessageMethod, SuccessfulPostMessageResponse,
            ErrorPostMessageResponse, PostMessageRequest>() {

}
