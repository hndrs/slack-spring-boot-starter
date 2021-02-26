package io.hndrs.slack.api.contract.jackson.group.usergroups.users

import io.hndrs.slack.api.contract.jackson.group.usergroups.UserGroup
import io.hndrs.slack.api.contract.jackson.group.usergroups.sample

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() =
    SuccessfulUsergroupUsersUpdateResponse(true, UserGroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun UsergroupUsersUpdateRequest.Companion.sample() = UsergroupUsersUpdateRequest(
    "",
    listOf("", ""),
    1
)
