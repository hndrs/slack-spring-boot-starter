package com.kreait.slack.api.contract.jackson.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse

fun AuthRevokeRequest.Companion.sample(): AuthRevokeRequest {
    return AuthRevokeRequest(true)
}

fun SuccessfulAuthRevokeResponse.Companion.sample(): SuccessfulAuthRevokeResponse {
    return SuccessfulAuthRevokeResponse(true, true)
}

fun ErrorAuthRevokeResponse.Companion.sample(): ErrorAuthRevokeResponse {
    return ErrorAuthRevokeResponse(true, "")
}
