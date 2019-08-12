package com.kreait.slack.api.contract.jackson.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.Usergroup
import com.kreait.slack.api.contract.jackson.group.usergroups.sample

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() = SuccessfulUsergroupUsersUpdateResponse(true, Usergroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun SlackUsergroupUsersUpdateRequest.Companion.sample() = SlackUsergroupUsersUpdateRequest(
        "",
        listOf("", ""),
        1)
