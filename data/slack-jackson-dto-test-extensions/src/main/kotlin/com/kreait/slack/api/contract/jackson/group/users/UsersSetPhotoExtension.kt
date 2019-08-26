package com.kreait.slack.api.contract.jackson.group.users

import java.io.File

fun SuccessfulSetPhotoResponse.Companion.sample() = SuccessfulSetPhotoResponse(ok = true)

fun ErrorSetPhotoResponse.Companion.sample() = ErrorSetPhotoResponse(ok = false, error = "")

fun SetPhotoRequest.Companion.sample(): SetPhotoRequest {
    return SetPhotoRequest(File(ContextHolder::class.java.classLoader.getResource("olaph.png").toURI()))
}


class ContextHolder