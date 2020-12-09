package com.kreait.slack.api.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Provide custom unfurl behavior for user-posted URLs
 * https://api.slack.com/methods/chat.unfurl
 */
abstract class ChatUnfurlMethod :
    ApiCallMethod<ChatUnfurlMethod, SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse, ChatUnfurlRequest>() {

}
