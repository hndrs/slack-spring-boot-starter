package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample

fun GroupsHistoryRequest.Companion.sample(): GroupsHistoryRequest {
    return GroupsHistoryRequest("")
}

fun SuccessfulGroupsHistoryResponse.Companion.sample(): SuccessfulGroupsHistoryResponse {
    return SuccessfulGroupsHistoryResponse(true, InstantSample.sample(), listOf(Message.sample()), false)
}

fun ErrorGroupsHistoryResponse.Companion.sample(): ErrorGroupsHistoryResponse {
    return ErrorGroupsHistoryResponse(true, "")
}




