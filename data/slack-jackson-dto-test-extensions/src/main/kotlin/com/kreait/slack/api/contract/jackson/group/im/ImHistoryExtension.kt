package com.kreait.slack.api.contract.jackson.group.im

fun SuccessfulImHistoryResponse.Companion.sample() = SuccessfulImHistoryResponse(true, "now", listOf(), false)

fun ErrorImHistoryResponse.Companion.sample() = ErrorImHistoryResponse(false, "")

fun ImHistoryRequest.Companion.sample() = ImHistoryRequest("")

fun SuccessfulImHistoryResponse.Message.Companion.sample() = SuccessfulImHistoryResponse.Message("", "")
