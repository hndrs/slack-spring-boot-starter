package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsLeaveRequest.Companion.sample(): GroupsLeaveRequest {
    return GroupsLeaveRequest("")
}

fun SuccessfulGroupsLeaveResponse.Companion.sample(): SuccessfulGroupsLeaveResponse {
    return SuccessfulGroupsLeaveResponse(true)
}

fun ErrorGroupsLeaveResponse.Companion.sample(): ErrorGroupsLeaveResponse {
    return ErrorGroupsLeaveResponse(true, "")
}




