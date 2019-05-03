package io.olaph.slack.dto.jackson.group.im

fun SuccessfulImListResponse.Companion.sample(): SuccessfulImListResponse {
    return SuccessfulImListResponse(true, listOf(Im.sample()), ResponseMetadata.sample())
}

fun ErrorImListResponse.Companion.sample(): ErrorImListResponse {
    return ErrorImListResponse(false, "")
}

fun SlackImListRequest.Companion.sample(): SlackImListRequest {
    return SlackImListRequest("", "")
}

fun Im.Companion.sample(): Im {
    return Im(0, "", false, false, false, "")
}

fun ResponseMetadata.Companion.sample(): ResponseMetadata {
    return ResponseMetadata("")
}