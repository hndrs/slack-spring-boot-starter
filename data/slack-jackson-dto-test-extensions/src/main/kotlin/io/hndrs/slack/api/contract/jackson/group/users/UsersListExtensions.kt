package io.hndrs.slack.api.contract.jackson.group.users

import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.sample

fun SuccessfulListResponse.Companion.sample(): SuccessfulListResponse {
    return SuccessfulListResponse(true, listOf(), 0, ResponseMetadata.sample())
}

fun ErrorListResponse.Companion.sample(): ErrorListResponse {
    return ErrorListResponse(false, "")
}

fun ListRequest.Companion.sample(): ListRequest {
    return ListRequest(false, 0, null)
}
