package com.kreait.slack.api.contract.jackson.group.im

import java.time.Instant

fun SuccessfulImMarkResponse.Companion.sample(): SuccessfulImMarkResponse {
    return SuccessfulImMarkResponse(true)
}

fun ErrorImMarkResponse.Companion.sample(): ErrorImMarkResponse {
    return ErrorImMarkResponse(false, "")
}

fun ImMarkRequest.Companion.sample(): ImMarkRequest {
    return ImMarkRequest("", Instant.now())
}
