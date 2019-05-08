package io.olaph.slack.dto.jackson.group.im

fun SuccessfulImHistoryResponse.Companion.sample() = SuccessfulImHistoryResponse(true, "now", listOf(), false)

fun ErrorImHistoryResponse.Companion.sample() = ErrorImHistoryResponse(false, "")

fun SlackImHistoryRequest.Companion.sample() = SlackImHistoryRequest("")

fun SuccessfulImHistoryResponse.Message.Companion.sample() = SuccessfulImHistoryResponse.Message("", "")