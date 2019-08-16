package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.common.types.sample

fun GroupsCreateRequest.Companion.sample(): GroupsCreateRequest {
    return GroupsCreateRequest("")
}

fun SuccessfulGroupsCreateResponse.Companion.sample(): SuccessfulGroupsCreateResponse {
    return SuccessfulGroupsCreateResponse(true, Group.sample())
}

fun ErrorGroupsCreateResponse.Companion.sample(): ErrorGroupsCreateResponse {
    return ErrorGroupsCreateResponse(true, "")
}




