package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersDeletePhotoResponse

fun SuccessfulUsersDeletePhotoResponse.Companion.sample(): SuccessfulUsersDeletePhotoResponse {
    return SuccessfulUsersDeletePhotoResponse(true)
}

fun ErrorUsersDeletePhotoResponse.Companion.sample(): ErrorUsersDeletePhotoResponse {
    return ErrorUsersDeletePhotoResponse(false, "")
}
