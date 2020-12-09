package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sends a message to a user in a channel.
 * https://api.slack.com/methods/chat.postMessage
 */
abstract class ChatPostMessageMethod :
    ApiCallMethod<ChatPostMessageMethod, SuccessfulPostMessageResponse,
            ErrorPostMessageResponse, PostMessageRequest>() {

}
