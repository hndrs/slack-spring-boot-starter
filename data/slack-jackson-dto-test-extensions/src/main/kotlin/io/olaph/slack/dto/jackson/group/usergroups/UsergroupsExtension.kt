package io.olaph.slack.dto.jackson.group.usergroups

import io.olaph.slack.dto.jackson.common.types.AutoType
import io.olaph.slack.dto.jackson.common.types.Prefs
import io.olaph.slack.dto.jackson.common.types.Usergroup

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