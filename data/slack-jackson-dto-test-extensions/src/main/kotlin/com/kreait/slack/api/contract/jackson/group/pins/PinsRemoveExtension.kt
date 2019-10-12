package com.kreait.slack.api.contract.jackson.group.pins

fun PinsRemoveRequest.Companion.sample(): PinsRemoveRequest {
    return PinsRemoveRequest("", fileId = "")
}

fun SuccessfulPinsRemoveResponse.Companion.sample(): SuccessfulPinsRemoveResponse {
    return SuccessfulPinsRemoveResponse(true)
}

fun ErrorPinsRemoveResponse.Companion.sample(): ErrorPinsRemoveResponse {
    return ErrorPinsRemoveResponse(true, "")
}
