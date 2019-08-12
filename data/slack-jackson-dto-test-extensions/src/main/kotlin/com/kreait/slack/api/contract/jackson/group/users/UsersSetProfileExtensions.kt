package com.kreait.slack.api.contract.jackson.group.users

fun SuccessfulUsersSetProfileResponse.Companion.sample(): SuccessfulUsersSetProfileResponse {
    return SuccessfulUsersSetProfileResponse(true, SuccessfulUsersSetProfileResponse.Profile.sample())
}

fun SuccessfulUsersSetProfileResponse.Profile.Companion.sample(): SuccessfulUsersSetProfileResponse.Profile {
    return SuccessfulUsersSetProfileResponse.Profile("", "", "", "",
            mapOf(), "", "", "", "", "", "", "",
            "", "", "", "", "", "", 0, "",
            "", "")
}

fun ErrorUsersSetProfileResponse.Companion.sample(): ErrorUsersSetProfileResponse {
    return ErrorUsersSetProfileResponse(false, "")
}
