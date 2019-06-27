package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.common.types.Member
import io.olaph.slack.dto.jackson.common.types.sample


fun SuccessfulUsersInfoResponse.Companion.sample(): SuccessfulUsersInfoResponse {
    return SuccessfulUsersInfoResponse(true, Member.sample())
}

fun ErrorUsersInfoResponse.Companion.sample(): ErrorUsersInfoResponse {
    return ErrorUsersInfoResponse(false, "")
}

fun SlackUserInfoRequest.Companion.sample(): SlackUserInfoRequest {
    return SlackUserInfoRequest("", false)
}