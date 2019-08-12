package com.kreait.slack.api.contract.jackson.group.users

fun SuccessfulSetPhotoResponse.Companion.sample() = SuccessfulSetPhotoResponse(ok = true)

fun ErrorSetPhotoResponse.Companion.sample() = ErrorSetPhotoResponse(ok = false, error = "")
