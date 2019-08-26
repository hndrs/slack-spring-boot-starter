package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.getJarFileUri
import java.io.File

fun SuccessfulSetPhotoResponse.Companion.sample() = SuccessfulSetPhotoResponse(ok = true)

fun ErrorSetPhotoResponse.Companion.sample() = ErrorSetPhotoResponse(ok = false, error = "")

fun SetPhotoRequest.Companion.sample(): SetPhotoRequest {
    return SetPhotoRequest(File(getJarFileUri("olaph.png")))
}

class ContextHolder