package com.kreait.slack.api.contract.jackson.group.team

fun SuccessfulProfileResponse.Companion.sample(): SuccessfulProfileResponse {
    return SuccessfulProfileResponse(true, TeamProfile.sample())
}

fun TeamProfile.Companion.sample(): TeamProfile {
    return TeamProfile(listOf(TeamProfile.Field.sample()))
}

fun TeamProfile.Field.Companion.sample(): TeamProfile.Field {
    return TeamProfile.Field("", "", 0, "", "", 0, "", "")
}

fun ErrorProfileResponse.Companion.sample(): ErrorProfileResponse {
    return ErrorProfileResponse(false, "")
}

fun ProfileRequest.Companion.sample(): ProfileRequest {
    return ProfileRequest(TeamVisibility.ALL)
}
