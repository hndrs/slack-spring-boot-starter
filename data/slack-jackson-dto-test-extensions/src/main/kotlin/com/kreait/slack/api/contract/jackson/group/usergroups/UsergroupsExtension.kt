package com.kreait.slack.api.contract.jackson.group.usergroups

fun UserGroup.Companion.sample() = UserGroup(id = "",
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
