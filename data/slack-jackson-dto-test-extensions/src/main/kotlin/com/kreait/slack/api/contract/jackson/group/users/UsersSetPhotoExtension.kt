package com.kreait.slack.api.contract.jackson.group.users

fun SuccessfulSetPhotoResponse.Companion.sample() = SuccessfulSetPhotoResponse(ok = true)

fun ErrorSetPhotoResponse.Companion.sample() = ErrorSetPhotoResponse(ok = false, error = "")

fun SetPhotoRequest.Companion.sample(): SetPhotoRequest {
    return SetPhotoRequest(javaClass.getResourceAsStream("/olaph.png"), 10, 10, 10)
}
