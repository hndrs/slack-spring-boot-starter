package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsKickRequest.Companion.sample(): GroupsKickRequest {
    return GroupsKickRequest("")
}

fun SuccessfulGroupsKickResponse.Companion.sample(): SuccessfulGroupsKickResponse {
    return SuccessfulGroupsKickResponse(true)
}

fun ErrorGroupsKickResponse.Companion.sample(): ErrorGroupsKickResponse {
    return ErrorGroupsKickResponse(true, "")
}




