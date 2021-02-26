package io.hndrs.slack.api.contract.jackson.group.users

fun SuccessfulSetProfileResponse.Companion.sample(): SuccessfulSetProfileResponse {
    return SuccessfulSetProfileResponse(true, SuccessfulSetProfileResponse.Profile.sample())
}

fun SuccessfulSetProfileResponse.Profile.Companion.sample(): SuccessfulSetProfileResponse.Profile {
    return SuccessfulSetProfileResponse.Profile(
        "", "", "", "",
        mapOf(), "", "", "", "", "", "", "",
        "", "", "", "", "", "", 0, "",
        "", ""
    )
}

fun ErrorSetProfileResponse.Companion.sample(): ErrorSetProfileResponse {
    return ErrorSetProfileResponse(false, "")
}

fun SetProfileRequest.Companion.sample() = SetProfileRequest("", mapOf(), "", "")
