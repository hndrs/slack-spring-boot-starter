package com.kreait.slack.api.contract.jackson.group.im

import com.kreait.slack.api.contract.jackson.common.InstantSample

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
    return Im(InstantSample.sample(), "", isIm = false, isOrgShared = false, isUserDeleted = false, user = "")
}

fun ResponseMetadata.Companion.sample(): ResponseMetadata {
    return ResponseMetadata("")
}
