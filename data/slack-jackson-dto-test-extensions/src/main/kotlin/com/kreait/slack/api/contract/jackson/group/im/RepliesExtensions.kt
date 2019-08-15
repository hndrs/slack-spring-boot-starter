package com.kreait.slack.api.contract.jackson.group.im

import java.time.Instant

fun SuccessfulImRepliesResponse.Companion.sample(): SuccessfulImRepliesResponse {
    return SuccessfulImRepliesResponse(true, listOf(SuccessfulImRepliesResponse.Message.sample()))
}

fun SuccessfulImRepliesResponse.Message.Companion.sample(): SuccessfulImRepliesResponse.Message {
    return SuccessfulImRepliesResponse.Message("text", Instant.ofEpochSecond(10000))
}

fun ErrorImRepliesResponse.Companion.sample(): ErrorImRepliesResponse = ErrorImRepliesResponse(false, "error")

fun ImRepliesRequest.Companion.sample(): ImRepliesRequest = ImRepliesRequest("channeId", "1234567890.123456")
