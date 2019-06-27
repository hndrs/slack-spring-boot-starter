package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.sample

fun SuccessfulUserListResponse.Companion.sample(): SuccessfulUserListResponse {
    return SuccessfulUserListResponse(true, listOf(), 0, ResponseMetadata.sample())
}

fun ErrorUserListResponse.Companion.sample(): ErrorUserListResponse {
    return ErrorUserListResponse(false, "")
}

fun SlackUserListRequest.Companion.sample(): SlackUserListRequest {
    return SlackUserListRequest(false, 0, false, "")
}