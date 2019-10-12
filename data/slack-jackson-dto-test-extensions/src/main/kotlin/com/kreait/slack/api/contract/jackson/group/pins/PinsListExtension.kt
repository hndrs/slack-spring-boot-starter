package com.kreait.slack.api.contract.jackson.group.pins

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun PinsListRequest.Companion.sample(): PinsListRequest {
    return PinsListRequest("", InstantSample.sample())
}

fun SuccessfulPinsListResponse.Companion.sample(): SuccessfulPinsListResponse {
    return SuccessfulPinsListResponse(true, listOf(PinsItem.Companion.sample()))
}

fun ErrorPinsListResponse.Companion.sample(): ErrorPinsListResponse {
    return ErrorPinsListResponse(true, "")
}

fun PinsItem.Companion.sample(): PinsItem {
    return PinsItem(
        channel = "",
        created = InstantSample.sample(),
        createdBy = "",
        type = PinsItem.Type.FILE
    )
}
