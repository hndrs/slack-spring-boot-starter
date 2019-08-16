package com.kreait.slack.api.contract.jackson.group.groups

import java.security.acl.Group

fun GroupsCreateChildRequest.Companion.sample(): GroupsCreateChildRequest {
    return GroupsCreateChildRequest("")
}

fun SuccessfulGroupsCreateChildResponse.Companion.sample(): SuccessfulGroupsCreateChildResponse {
    return SuccessfulGroupsCreateChildResponse(true, Group.sample())
}

fun ErrorGroupsCreateChildResponse.Companion.sample(): ErrorGroupsCreateChildResponse {
    return ErrorGroupsCreateChildResponse(true, "")
}




