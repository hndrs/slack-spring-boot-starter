package com.kreait.slack.api.contract.jackson.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse

fun SuccessfulImMarkResponse.Companion.sample(): SuccessfulImMarkResponse {
    return SuccessfulImMarkResponse(true)
}

fun ErrorImMarkResponse.Companion.sample(): ErrorImMarkResponse {
    return ErrorImMarkResponse(false, "")
}

fun SlackImMarkRequest.Companion.sample(): SlackImMarkRequest {
    return SlackImMarkRequest("", "")
}
