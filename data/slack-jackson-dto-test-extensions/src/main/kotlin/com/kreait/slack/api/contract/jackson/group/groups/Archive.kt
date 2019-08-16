package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsArchiveRequest.Companion.sample(): GroupsArchiveRequest {
    return GroupsArchiveRequest("")
}

fun SuccessfulGroupsArchiveResponse.Companion.sample(): SuccessfulGroupsArchiveResponse {
    return SuccessfulGroupsArchiveResponse(true)
}

fun ErrorGroupsArchiveResponse.Companion.sample(): ErrorGroupsArchiveResponse {
    return ErrorGroupsArchiveResponse(true, "")
}




