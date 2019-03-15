package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUserListResponse.Companion.sample(): SuccessfulUserListResponse {
    return SuccessfulUserListResponse(true, listOf(), 0)
}

fun Member.Companion.sample(): Member {
    return Member("", "", "", false, "", "", "", "", 0, UserProfile.sample(),
            false, false, false, false, false, false, 0, false, false)
}

fun UserProfile.Companion.sample(): UserProfile {
    return UserProfile("", "", "", "", "", "", "", "", "", "",
            0, "", false, "", "", "", "", "", "", "", "", "", "", "", "", "")
}

fun ErrorUserListResponse.Companion.sample(): ErrorUserListResponse {
    return ErrorUserListResponse(false, "")
}