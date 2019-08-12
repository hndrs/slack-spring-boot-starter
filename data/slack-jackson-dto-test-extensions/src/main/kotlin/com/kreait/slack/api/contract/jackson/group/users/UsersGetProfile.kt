package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetProfileRequest
import com.kreait.slack.api.contract.jackson.common.types.UserProfile

fun SuccessfulUsersGetProfileResponse.Companion.sample(): SuccessfulUsersGetProfileResponse {
    return SuccessfulUsersGetProfileResponse(true, UserProfile.sample())
}

fun ErrorUsersGetProfileResponse.Companion.sample(): ErrorUsersGetProfileResponse {
    return ErrorUsersGetProfileResponse(true, "")
}

fun UsersGetProfileRequest.Companion.sample(): UsersGetProfileRequest {
    return UsersGetProfileRequest(false, "")
}
