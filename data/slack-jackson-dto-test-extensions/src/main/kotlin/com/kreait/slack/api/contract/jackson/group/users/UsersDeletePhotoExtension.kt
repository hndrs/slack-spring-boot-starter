package com.kreait.slack.api.contract.jackson.group.users

fun SuccessfulDeletePhotoResponse.Companion.sample(): SuccessfulDeletePhotoResponse {
    return SuccessfulDeletePhotoResponse(true)
}

fun ErrorDeletePhotoResponse.Companion.sample(): ErrorDeletePhotoResponse {
    return ErrorDeletePhotoResponse(false, "")
}
