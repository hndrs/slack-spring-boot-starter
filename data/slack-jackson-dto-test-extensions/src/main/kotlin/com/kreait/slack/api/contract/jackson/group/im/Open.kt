package com.kreait.slack.api.contract.jackson.group.im

fun SuccessfulImOpenResponse.Companion.sample(): SuccessfulImOpenResponse {
    return SuccessfulImOpenResponse(true, SuccessfulImOpenResponse.Channel.sample())
}

fun SuccessfulImOpenResponse.Channel.Companion.sample(): SuccessfulImOpenResponse.Channel {
    return SuccessfulImOpenResponse.Channel("")
}

fun ErrorImOpenResponse.Companion.sample(): ErrorImOpenResponse {
    return ErrorImOpenResponse(false, "")
}

fun SlackImOpenRequest.Companion.sample(): SlackImOpenRequest {
    return SlackImOpenRequest("")
}
