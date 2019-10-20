package com.kreait.slack.api.contract.jackson.group.stars

fun StarsRemoveRequest.Companion.sample(): StarsRemoveRequest {
    return StarsRemoveRequest()
}

fun SuccessfulStarsRemoveResponse.Companion.sample(): SuccessfulStarsRemoveResponse {
    return SuccessfulStarsRemoveResponse(true)
}

fun ErrorStarsRemoveResponse.Companion.sample(): ErrorStarsRemoveResponse {
    return ErrorStarsRemoveResponse(true, "")
}
