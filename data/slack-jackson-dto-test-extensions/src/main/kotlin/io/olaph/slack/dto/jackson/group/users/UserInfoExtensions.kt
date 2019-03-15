package io.olaph.slack.dto.jackson.group.users


fun SuccessfulUsersInfoResponse.Companion.sample(): SuccessfulUsersInfoResponse {
    return SuccessfulUsersInfoResponse(true, User.sample())
}

fun User.Companion.sample(): User {
    return User("", "", "", false, "", "", "", "", 0, Profile.sample(), false, false,
            false, false, false, false, false, 0, false, "")
}

fun Profile.Companion.sample(): Profile {
    return Profile("", "", "", "", "", "", "", "", "",
            "", 0, "", "", "", "", "", "", "", "", "", "")
}

fun ErrorUsersInfoResponse.Companion.sample(): ErrorUsersInfoResponse {
    return ErrorUsersInfoResponse(false, "")
}

fun SlackUserInfoRequest.Companion.sample(): SlackUserInfoRequest {
    return SlackUserInfoRequest("", false)
}