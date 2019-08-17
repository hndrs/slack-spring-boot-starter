package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.common.types.sample

fun GroupsInviteRequest.Companion.sample(): GroupsInviteRequest {
    return GroupsInviteRequest("", "")
}

fun SuccessfulGroupsInviteResponse.Companion.sample(): SuccessfulGroupsInviteResponse {
    return SuccessfulGroupsInviteResponse(true, Group.sample(), null)
}

fun ErrorGroupsInviteResponse.Companion.sample(): ErrorGroupsInviteResponse {
    return ErrorGroupsInviteResponse(true, "")
}




