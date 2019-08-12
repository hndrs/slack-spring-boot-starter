package com.kreait.slack.api.contract.jackson.group.im

fun SuccessfulImRepliesResponse.Companion.sample(): SuccessfulImRepliesResponse {
    return SuccessfulImRepliesResponse(true, listOf(SuccessfulImRepliesResponse.Message.sample()))
}

fun SuccessfulImRepliesResponse.Message.Companion.sample(): SuccessfulImRepliesResponse.Message {
    return SuccessfulImRepliesResponse.Message("text", "1234567890.123456")
}

fun ErrorImRepliesResponse.Companion.sample(): ErrorImRepliesResponse = ErrorImRepliesResponse(false, "error")

fun SlackImRepliesRequest.Companion.sample(): SlackImRepliesRequest = SlackImRepliesRequest("channeId", "1234567890.123456")
