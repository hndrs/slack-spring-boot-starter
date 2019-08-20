package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsUnarchiveRequest.Companion.sample(): GroupsUnarchiveRequest {
    return GroupsUnarchiveRequest("")
}

fun SuccessfulGroupsUnarchiveResponse.Companion.sample(): SuccessfulGroupsUnarchiveResponse {
    return SuccessfulGroupsUnarchiveResponse(true)
}

fun ErrorGroupsUnarchiveResponse.Companion.sample(): ErrorGroupsUnarchiveResponse {
    return ErrorGroupsUnarchiveResponse(true, "")
}




