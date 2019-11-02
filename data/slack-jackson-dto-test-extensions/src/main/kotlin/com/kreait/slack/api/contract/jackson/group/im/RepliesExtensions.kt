package com.kreait.slack.api.contract.jackson.group.im

import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample

fun SuccessfulImRepliesResponse.Companion.sample(): SuccessfulImRepliesResponse {
    return SuccessfulImRepliesResponse(true, listOf(Message.sample()))
}

fun ErrorImRepliesResponse.Companion.sample(): ErrorImRepliesResponse = ErrorImRepliesResponse(false, "error")

fun ImRepliesRequest.Companion.sample(): ImRepliesRequest = ImRepliesRequest("channeId", "1234567890.123456")
