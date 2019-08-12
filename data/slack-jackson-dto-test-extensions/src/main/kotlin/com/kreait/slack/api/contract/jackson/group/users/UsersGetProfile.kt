package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.common.types.UserProfile
import com.kreait.slack.api.contract.jackson.common.types.sample

fun SuccessfulGetProfileResponse.Companion.sample(): SuccessfulGetProfileResponse {
    return SuccessfulGetProfileResponse(true, UserProfile.sample())
}

fun ErrorGetProfileResponse.Companion.sample(): ErrorGetProfileResponse {
    return ErrorGetProfileResponse(true, "")
}

fun GetProfileRequest.Companion.sample(): GetProfileRequest {
    return GetProfileRequest(false, "")
}

