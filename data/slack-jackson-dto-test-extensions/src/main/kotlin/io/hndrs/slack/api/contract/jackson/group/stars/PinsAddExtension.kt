package io.hndrs.slack.api.contract.jackson.group.stars

fun StarsAddRequest.Companion.sample(): StarsAddRequest {
    return StarsAddRequest(
        channel = ""
    )
}

fun SuccessfulStarsAddResponse.Companion.sample(): SuccessfulStarsAddResponse {
    return SuccessfulStarsAddResponse(true)
}

fun ErrorStarsAddResponse.Companion.sample(): ErrorStarsAddResponse {
    return ErrorStarsAddResponse(true, "")
}
