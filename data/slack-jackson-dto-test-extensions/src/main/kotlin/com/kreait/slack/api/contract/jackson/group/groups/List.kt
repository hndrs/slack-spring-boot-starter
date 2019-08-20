package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample
import com.kreait.slack.api.contract.jackson.common.types.Group
import com.kreait.slack.api.contract.jackson.common.types.sample

fun GroupsListRequest.Companion.sample(): GroupsListRequest {
    return GroupsListRequest("")
}

fun SuccessfulGroupsListResponse.Companion.sample(): SuccessfulGroupsListResponse {
    return SuccessfulGroupsListResponse(true, listOf(Group.sample()), ResponseMetadata.sample())
}

fun ErrorGroupsListResponse.Companion.sample(): ErrorGroupsListResponse {
    return ErrorGroupsListResponse(true, "")
}




