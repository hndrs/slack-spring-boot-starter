package com.kreait.slack.api.contract.jackson.group.im

import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse

fun SuccessfulImHistoryResponse.Companion.sample() = SuccessfulImHistoryResponse(true, "now", listOf(), false)

fun ErrorImHistoryResponse.Companion.sample() = ErrorImHistoryResponse(false, "")

fun SlackImHistoryRequest.Companion.sample() = SlackImHistoryRequest("")

fun SuccessfulImHistoryResponse.Message.Companion.sample() = SuccessfulImHistoryResponse.Message("", "")
