package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsOpenRequest.Companion.sample(): GroupsOpenRequest {
    return GroupsOpenRequest("")
}

fun SuccessfulGroupsOpenResponse.Companion.sample(): SuccessfulGroupsOpenResponse {
    return SuccessfulGroupsOpenResponse(true, true, true)
}

fun ErrorGroupsOpenResponse.Companion.sample(): ErrorGroupsOpenResponse {
    return ErrorGroupsOpenResponse(true, "")
}




