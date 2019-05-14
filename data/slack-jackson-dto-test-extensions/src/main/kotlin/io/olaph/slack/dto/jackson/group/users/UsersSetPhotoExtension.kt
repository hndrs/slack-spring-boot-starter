package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUsersSetPhotoResponse.Companion.sample() = SuccessfulUsersSetPhotoResponse(ok = true)

fun ErrorUsersSetPhotoResponse.Companion.sample() = ErrorUsersSetPhotoResponse(ok = false, error = "")
