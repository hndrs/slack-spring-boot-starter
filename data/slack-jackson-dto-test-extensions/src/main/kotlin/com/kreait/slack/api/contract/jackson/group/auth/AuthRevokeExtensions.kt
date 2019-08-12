package com.kreait.slack.api.contract.jackson.group.auth

fun AuthRevokeRequest.Companion.sample(): AuthRevokeRequest {
    return AuthRevokeRequest(true)
}

fun SuccessfulAuthRevokeResponse.Companion.sample(): SuccessfulAuthRevokeResponse {
    return SuccessfulAuthRevokeResponse(true, true)
}

fun ErrorAuthRevokeResponse.Companion.sample(): ErrorAuthRevokeResponse {
    return ErrorAuthRevokeResponse(true, "")
}
