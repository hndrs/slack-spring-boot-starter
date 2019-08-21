package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun GroupsRepliesRequest.Companion.sample(): GroupsRepliesRequest {
    return GroupsRepliesRequest("", InstantSample.sample())
}

fun SuccessfulGroupsRepliesResponse.Companion.sample(): SuccessfulGroupsRepliesResponse {
    return SuccessfulGroupsRepliesResponse(true, listOf(SuccessfulGroupsHistoryResponse.Message.sample()))
}

fun ErrorGroupsRepliesResponse.Companion.sample(): ErrorGroupsRepliesResponse {
    return ErrorGroupsRepliesResponse(true, "")
}




