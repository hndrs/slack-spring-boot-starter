package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.common.types.sample

fun GroupsInfoRequest.Companion.sample(): GroupsInfoRequest {
    return GroupsInfoRequest("")
}

fun SuccessfulGroupsInfoResponse.Companion.sample(): SuccessfulGroupsInfoResponse {
    return SuccessfulGroupsInfoResponse(true, Group.sample())
}

fun ErrorGroupsInfoResponse.Companion.sample(): ErrorGroupsInfoResponse {
    return ErrorGroupsInfoResponse(true, "")
}




