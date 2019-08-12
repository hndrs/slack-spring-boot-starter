package com.kreait.slack.api.contract.jackson.group.im

fun SuccessfulImListResponse.Companion.sample(): SuccessfulImListResponse {
    return SuccessfulImListResponse(true, listOf(Im.sample()), ResponseMetadata.sample())
}

fun ErrorImListResponse.Companion.sample(): ErrorImListResponse {
    return ErrorImListResponse(false, "")
}

fun ImListRequest.Companion.sample(): ImListRequest {
    return ImListRequest("", "")
}

fun Im.Companion.sample(): Im {
    return Im(0, "", false, false, false, "")
}

fun ResponseMetadata.Companion.sample(): ResponseMetadata {
    return ResponseMetadata("")
}
