package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample

fun SuccessfulUserListResponse.Companion.sample(): SuccessfulUserListResponse {
    return SuccessfulUserListResponse(true, listOf(), 0, ResponseMetadata.sample())
}

fun ErrorUserListResponse.Companion.sample(): ErrorUserListResponse {
    return ErrorUserListResponse(false, "")
}

fun SlackUserListRequest.Companion.sample(): SlackUserListRequest {
    return SlackUserListRequest(false, 0, false, "test")
}
