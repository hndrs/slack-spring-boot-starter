package com.kreait.slack.api.contract.jackson.group.groups

fun GroupsSetPurposeRequest.Companion.sample(): GroupsSetPurposeRequest {
    return GroupsSetPurposeRequest("", "")
}

fun SuccessfulGroupsSetPurposeResponse.Companion.sample(): SuccessfulGroupsSetPurposeResponse {
    return SuccessfulGroupsSetPurposeResponse(true, "")
}

fun ErrorGroupsSetPurposeResponse.Companion.sample(): ErrorGroupsSetPurposeResponse {
    return ErrorGroupsSetPurposeResponse(true, "")
}