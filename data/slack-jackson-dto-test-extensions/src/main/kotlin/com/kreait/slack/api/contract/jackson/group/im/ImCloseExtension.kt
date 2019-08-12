package com.kreait.slack.api.contract.jackson.group.im

fun SuccessfulImCloseResponse.Companion.sample(): SuccessfulImCloseResponse {
    return SuccessfulImCloseResponse(true, false, false)
}

fun ErrorImCloseResponse.Companion.sample(): ErrorImCloseResponse {
    return ErrorImCloseResponse(false, "")
}

fun ImCloseRequest.Companion.sample(): ImCloseRequest {
    return ImCloseRequest("")
}
