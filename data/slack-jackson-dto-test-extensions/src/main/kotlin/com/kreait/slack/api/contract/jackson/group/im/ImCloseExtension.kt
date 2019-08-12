package com.kreait.slack.api.contract.jackson.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse

fun SuccessfulImCloseResponse.Companion.sample(): SuccessfulImCloseResponse {
    return SuccessfulImCloseResponse(true, false, false)
}

fun ErrorImCloseResponse.Companion.sample(): ErrorImCloseResponse {
    return ErrorImCloseResponse(false, "")
}

fun SlackImCloseRequest.Companion.sample(): SlackImCloseRequest {
    return SlackImCloseRequest("")
}
