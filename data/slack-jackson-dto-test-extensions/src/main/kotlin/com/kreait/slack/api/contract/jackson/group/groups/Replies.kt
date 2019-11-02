package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample

fun GroupsRepliesRequest.Companion.sample(): GroupsRepliesRequest {
    return GroupsRepliesRequest("", InstantSample.sample())
}

fun SuccessfulGroupsRepliesResponse.Companion.sample(): SuccessfulGroupsRepliesResponse {
    return SuccessfulGroupsRepliesResponse(true, listOf(Message.sample()))
}

fun ErrorGroupsRepliesResponse.Companion.sample(): ErrorGroupsRepliesResponse {
    return ErrorGroupsRepliesResponse(true, "")
}




