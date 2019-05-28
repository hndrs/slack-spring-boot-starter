package io.olaph.slack.dto.jackson.group.usergroups.users

import io.olaph.slack.dto.jackson.group.users.User
import io.olaph.slack.dto.jackson.group.users.sample

fun SuccessfulUsergroupsUsersListResponse.Companion.sample() = SuccessfulUsergroupsUsersListResponse(true, listOf(User.sample()))

fun ErrorUsergroupsUsersListResponse.Companion.sample() = ErrorUsergroupsUsersListResponse(false, "")

fun SlackUsergroupsUsersListRequest.Companion.sample() = SlackUsergroupsUsersListRequest("", true)