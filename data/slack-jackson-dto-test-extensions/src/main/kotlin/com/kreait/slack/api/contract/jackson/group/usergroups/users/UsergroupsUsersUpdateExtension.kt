package com.kreait.slack.api.contract.jackson.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.Usergroup
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() = SuccessfulUsergroupUsersUpdateResponse(true, Usergroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun SlackUsergroupUsersUpdateRequest.Companion.sample() = SlackUsergroupUsersUpdateRequest(
        "",
        listOf("", ""),
        1)
