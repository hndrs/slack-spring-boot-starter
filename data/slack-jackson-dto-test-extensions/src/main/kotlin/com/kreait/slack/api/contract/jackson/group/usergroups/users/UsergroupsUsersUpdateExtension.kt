package com.kreait.slack.api.contract.jackson.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.UserGroup
import com.kreait.slack.api.contract.jackson.group.usergroups.sample

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() =
    SuccessfulUsergroupUsersUpdateResponse(true, UserGroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun UsergroupUsersUpdateRequest.Companion.sample() = UsergroupUsersUpdateRequest(
    "",
    listOf("", ""),
    1
)
