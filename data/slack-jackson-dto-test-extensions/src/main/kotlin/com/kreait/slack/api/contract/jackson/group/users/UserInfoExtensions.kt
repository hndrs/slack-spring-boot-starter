package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.common.types.sample


fun SuccessfulUsersInfoResponse.Companion.sample(): SuccessfulUsersInfoResponse {
    return SuccessfulUsersInfoResponse(true, Member.sample())
}

fun ErrorUsersInfoResponse.Companion.sample(): ErrorUsersInfoResponse {
    return ErrorUsersInfoResponse(false, "")
}

fun SlackUserInfoRequest.Companion.sample(): SlackUserInfoRequest {
    return SlackUserInfoRequest("", false)
}
