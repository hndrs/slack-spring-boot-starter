package io.olaph.slack.dto.jackson.group.usergroups.users

import io.olaph.slack.dto.jackson.group.usergroups.Usergroup
import io.olaph.slack.dto.jackson.group.usergroups.sample

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() = SuccessfulUsergroupUsersUpdateResponse(true, Usergroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun SlackUsergroupUsersUpdateRequest.Companion.sample() = SlackUsergroupUsersUpdateRequest(
        "",
        listOf("", ""),
        1)
