package com.kreait.slack.api.contract.jackson.group.im

fun SuccessfulImCloseResponse.Companion.sample(): SuccessfulImCloseResponse {
    return SuccessfulImCloseResponse(true, false, false)
}

fun ErrorImCloseResponse.Companion.sample(): ErrorImCloseResponse {
    return ErrorImCloseResponse(false, "")
}

fun SlackImCloseRequest.Companion.sample(): SlackImCloseRequest {
    return SlackImCloseRequest("")
}
