package io.olaph.slack.dto.jackson.group.usergroups.users

import io.olaph.slack.dto.jackson.common.types.AutoType
import io.olaph.slack.dto.jackson.common.types.Prefs
import io.olaph.slack.dto.jackson.common.types.Usergroup
import io.olaph.slack.dto.jackson.group.usergroups.sample

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() = SuccessfulUsergroupUsersUpdateResponse(true, Usergroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun SlackUsergroupUsersUpdateRequest.Companion.sample() = SlackUsergroupUsersUpdateRequest(
        "",
        listOf("", ""),
        1)

fun Usergroup.Companion.sample() = Usergroup(id = "",
        teamId = "",
        isUsergroup = true,
        name = "",
        description = "",
        handle = "",
        isExternal = false,
        dateCreate = 0,
        dateUpdate = 0,
        dateDelete = 1,
        autoType = AutoType.NULL,
        createdBy = "",
        updatedBy = "",
        deletedBy = "",
        prefs = Prefs.sample(),
        users = listOf("", ""),
        userCount = 2)

fun Prefs.Companion.sample() = Prefs(listOf(""), listOf(""))
