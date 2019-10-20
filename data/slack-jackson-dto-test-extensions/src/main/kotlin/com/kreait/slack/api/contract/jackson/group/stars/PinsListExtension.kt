package com.kreait.slack.api.contract.jackson.group.stars

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample

fun StarsListRequest.Companion.sample(): StarsListRequest {
    return StarsListRequest()
}

fun SuccessfulStarsListResponse.Companion.sample(): SuccessfulStarsListResponse {
    return SuccessfulStarsListResponse(true, listOf(StarsItem.sample()), ResponseMetadata.sample())
}

fun ErrorStarsListResponse.Companion.sample(): ErrorStarsListResponse {
    return ErrorStarsListResponse(true, "")
}

fun StarsItem.Companion.sample(): StarsItem {
    return StarsItem(
        type = StarsItem.Type.CHANNEL,
        channel = ""
    )
}
