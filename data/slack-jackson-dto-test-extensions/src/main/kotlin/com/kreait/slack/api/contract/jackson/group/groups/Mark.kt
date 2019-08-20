package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsMarkRequest.Companion.sample(): GroupsMarkRequest {
    return GroupsMarkRequest("")
}

fun SuccessfulGroupsMarkResponse.Companion.sample(): SuccessfulGroupsMarkResponse {
    return SuccessfulGroupsMarkResponse(true)
}

fun ErrorGroupsMarkResponse.Companion.sample(): ErrorGroupsMarkResponse {
    return ErrorGroupsMarkResponse(true, "")
}




