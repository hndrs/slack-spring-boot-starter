package io.hndrs.slack.api.contract.jackson.group.usergroups

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun UserGroup.Companion.sample() = UserGroup(
    id = "",
    teamId = "",
    isUserGroup = true,
    name = "",
    description = "",
    handle = "",
    isExternal = false,
    createdAt = InstantSample.sample(),
    updatedAt = InstantSample.sample(),
    deletedAt = InstantSample.sample(),
    autoType = AutoType.NULL,
    createdBy = "",
    updatedBy = "",
    deletedBy = "",
    prefs = Prefs.sample(),
    userIds = listOf("", ""),
    userCount = 2
)

fun Prefs.Companion.sample() = Prefs(listOf(""), listOf(""))
