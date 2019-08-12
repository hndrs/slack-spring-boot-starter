package com.kreait.slack.api.contract.jackson.group.users

fun SuccessfulUsersDeletePhotoResponse.Companion.sample(): SuccessfulUsersDeletePhotoResponse {
    return SuccessfulUsersDeletePhotoResponse(true)
}

fun ErrorUsersDeletePhotoResponse.Companion.sample(): ErrorUsersDeletePhotoResponse {
    return ErrorUsersDeletePhotoResponse(false, "")
}
