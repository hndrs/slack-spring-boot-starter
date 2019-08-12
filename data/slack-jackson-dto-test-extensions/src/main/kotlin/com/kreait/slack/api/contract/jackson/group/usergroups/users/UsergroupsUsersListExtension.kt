package com.kreait.slack.api.contract.jackson.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse

fun SuccessfulUsergroupsUsersListResponse.Companion.sample() = SuccessfulUsergroupsUsersListResponse(true, listOf())

fun ErrorUsergroupsUsersListResponse.Companion.sample() = ErrorUsergroupsUsersListResponse(false, "")

fun SlackUsergroupsUsersListRequest.Companion.sample() = SlackUsergroupsUsersListRequest("", true)
