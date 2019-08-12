package com.kreait.slack.api.contract.jackson.group.usergroups.users

fun SuccessfulUsergroupsUsersListResponse.Companion.sample() = SuccessfulUsergroupsUsersListResponse(true, listOf())

fun ErrorUsergroupsUsersListResponse.Companion.sample() = ErrorUsergroupsUsersListResponse(false, "")

fun SlackUsergroupsUsersListRequest.Companion.sample() = SlackUsergroupsUsersListRequest("", true)
