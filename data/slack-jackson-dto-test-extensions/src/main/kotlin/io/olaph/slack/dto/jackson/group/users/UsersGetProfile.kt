package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUsersGetProfileResponse.Companion.sample(): SuccessfulUsersGetProfileResponse {
    return SuccessfulUsersGetProfileResponse(true, SuccessfulUsersGetProfileResponse.Profile.sample())
}

fun ErrorUsersGetProfileResponse.Companion.sample(): ErrorUsersGetProfileResponse {
    return ErrorUsersGetProfileResponse(true, "")
}

fun SuccessfulUsersGetProfileResponse.Profile.Companion.sample(): SuccessfulUsersGetProfileResponse.Profile {
    return SuccessfulUsersGetProfileResponse.Profile("", "", "", "", "", "", "",
            "", "", "", 0, "", "", "", "", "", "", "",
            "", "", "")
}

fun UsersGetProfileRequest.Companion.sample(): UsersGetProfileRequest {
    return UsersGetProfileRequest(false, "")
}

