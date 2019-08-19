package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun GroupsHistoryRequest.Companion.sample(): GroupsHistoryRequest {
    return GroupsHistoryRequest("")
}

fun SuccessfulGroupsHistoryResponse.Companion.sample(): SuccessfulGroupsHistoryResponse {
    return SuccessfulGroupsHistoryResponse(true, InstantSample.sample(), listOf(SuccessfulGroupsHistoryResponse.Message.sample()), false)
}

fun SuccessfulGroupsHistoryResponse.Message.Companion.sample(): SuccessfulGroupsHistoryResponse.Message {
    return SuccessfulGroupsHistoryResponse.Message(true, "", InstantSample.sample(), "", "")
}

fun ErrorGroupsHistoryResponse.Companion.sample(): ErrorGroupsHistoryResponse {
    return ErrorGroupsHistoryResponse(true, "")
}




