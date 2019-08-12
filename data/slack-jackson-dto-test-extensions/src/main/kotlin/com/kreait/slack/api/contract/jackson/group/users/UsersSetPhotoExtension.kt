package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPhotoResponse

fun SuccessfulUsersSetPhotoResponse.Companion.sample() = SuccessfulUsersSetPhotoResponse(ok = true)

fun ErrorUsersSetPhotoResponse.Companion.sample() = ErrorUsersSetPhotoResponse(ok = false, error = "")
