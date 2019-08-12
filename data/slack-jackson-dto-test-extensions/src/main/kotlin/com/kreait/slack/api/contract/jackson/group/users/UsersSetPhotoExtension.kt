package com.kreait.slack.api.contract.jackson.group.users

fun SuccessfulUsersSetPhotoResponse.Companion.sample() = SuccessfulUsersSetPhotoResponse(ok = true)

fun ErrorUsersSetPhotoResponse.Companion.sample() = ErrorUsersSetPhotoResponse(ok = false, error = "")
