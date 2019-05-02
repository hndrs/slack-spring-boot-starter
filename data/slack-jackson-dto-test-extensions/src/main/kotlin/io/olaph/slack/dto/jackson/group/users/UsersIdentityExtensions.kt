package io.olaph.slack.dto.jackson.group.users


fun SuccessfulUsersIdentityResponse.Companion.sample(): SuccessfulUsersIdentityResponse {
    return SuccessfulUsersIdentityResponse(true, Identity(Identity.User.sample(), Identity.Team.Companion.sample()))
}

fun Identity.User.Companion.sample(): Identity.User {
    return Identity.User("", "", "", "", "", "", "", "")
}

fun Identity.Team.Companion.sample(): Identity.Team {
    return Identity.Team("", "")
}

fun ErrorUsersIdentityResponse.Companion.sample(): ErrorUsersIdentityResponse {
    return ErrorUsersIdentityResponse(false, "")
}
