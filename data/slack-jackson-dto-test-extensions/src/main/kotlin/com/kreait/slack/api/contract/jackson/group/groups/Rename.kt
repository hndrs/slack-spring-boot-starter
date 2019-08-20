package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.common.types.sample

fun GroupsRenameRequest.Companion.sample(): GroupsRenameRequest {
    return GroupsRenameRequest("", "", false)
}

fun SuccessfulGroupsRenameResponse.Companion.sample(): SuccessfulGroupsRenameResponse {
    return SuccessfulGroupsRenameResponse(true, Group.sample())
}

fun ErrorGroupsRenameResponse.Companion.sample(): ErrorGroupsRenameResponse {
    return ErrorGroupsRenameResponse(true, "")
}




