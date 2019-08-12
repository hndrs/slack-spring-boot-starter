package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserInfoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersInfoResponse
import com.kreait.slack.api.contract.jackson.common.types.Member


fun SuccessfulUsersInfoResponse.Companion.sample(): SuccessfulUsersInfoResponse {
    return SuccessfulUsersInfoResponse(true, Member.sample())
}

fun ErrorUsersInfoResponse.Companion.sample(): ErrorUsersInfoResponse {
    return ErrorUsersInfoResponse(false, "")
}

fun SlackUserInfoRequest.Companion.sample(): SlackUserInfoRequest {
    return SlackUserInfoRequest("", false)
}
