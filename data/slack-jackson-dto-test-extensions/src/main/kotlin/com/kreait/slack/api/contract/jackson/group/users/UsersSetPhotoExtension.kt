package com.kreait.slack.api.contract.jackson.group.users

import java.io.File

fun SuccessfulSetPhotoResponse.Companion.sample() = SuccessfulSetPhotoResponse(ok = true)

fun ErrorSetPhotoResponse.Companion.sample() = ErrorSetPhotoResponse(ok = false, error = "")

fun SetPhotoRequest.Companion.sample() = SetPhotoRequest(File(this::class.java.getResource("/olaph.png").file), 10, 10, 10)
