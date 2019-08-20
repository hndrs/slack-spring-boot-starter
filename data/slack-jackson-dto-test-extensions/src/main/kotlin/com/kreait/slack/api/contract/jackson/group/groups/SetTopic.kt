package com.kreait.slack.api.contract.jackson.group.groups

import com.kreait.slack.api.contract.jackson.common.types.Topic
import com.kreait.slack.api.contract.jackson.common.types.sample

fun GroupsSetTopicRequest.Companion.sample(): GroupsSetTopicRequest {
    return GroupsSetTopicRequest("", Topic.sample())
}

fun SuccessfulGroupsSetTopicResponse.Companion.sample(): SuccessfulGroupsSetTopicResponse {
    return SuccessfulGroupsSetTopicResponse(true, Topic.sample())
}

fun ErrorGroupsSetTopicResponse.Companion.sample(): ErrorGroupsSetTopicResponse {
    return ErrorGroupsSetTopicResponse(true, "")
}




