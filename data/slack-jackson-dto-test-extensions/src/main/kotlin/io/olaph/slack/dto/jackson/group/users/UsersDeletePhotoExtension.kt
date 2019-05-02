package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUsersDeletePhotoResponse.Companion.sample(): SuccessfulUsersDeletePhotoResponse {
    return SuccessfulUsersDeletePhotoResponse(true)
}

fun ErrorUsersDeletePhotoResponse.Companion.sample(): ErrorUsersDeletePhotoResponse {
    return ErrorUsersDeletePhotoResponse(false, "")
}