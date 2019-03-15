package io.olaph.slack.dto.jackson.group.auth

fun SuccessfulAuthTestResponse.Companion.sample(): SuccessfulAuthTestResponse {
    return SuccessfulAuthTestResponse(true, "", "", "", "", "")
}
