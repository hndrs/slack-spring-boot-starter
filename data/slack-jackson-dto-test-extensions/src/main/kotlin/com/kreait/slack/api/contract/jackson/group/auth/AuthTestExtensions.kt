package com.kreait.slack.api.contract.jackson.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse

fun SuccessfulAuthTestResponse.Companion.sample(): SuccessfulAuthTestResponse {
    return SuccessfulAuthTestResponse(true, "", "", "", "", "")
}

fun ErrorAuthTestResponse.Companion.sample(): ErrorAuthTestResponse = ErrorAuthTestResponse(false, "error")
