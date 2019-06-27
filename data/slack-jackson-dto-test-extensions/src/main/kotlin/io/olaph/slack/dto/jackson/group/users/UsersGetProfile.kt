package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.common.types.UserProfile
import io.olaph.slack.dto.jackson.common.types.sample

fun SuccessfulUsersGetProfileResponse.Companion.sample(): SuccessfulUsersGetProfileResponse {
    return SuccessfulUsersGetProfileResponse(true, UserProfile.sample())
}

fun ErrorUsersGetProfileResponse.Companion.sample(): ErrorUsersGetProfileResponse {
    return ErrorUsersGetProfileResponse(true, "")
}

fun UsersGetProfileRequest.Companion.sample(): UsersGetProfileRequest {
    return UsersGetProfileRequest(false, "")
}

