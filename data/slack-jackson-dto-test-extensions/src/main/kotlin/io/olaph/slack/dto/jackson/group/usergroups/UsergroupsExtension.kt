package io.olaph.slack.dto.jackson.group.usergroups

import io.olaph.slack.dto.jackson.common.types.AutoType
import io.olaph.slack.dto.jackson.common.types.Prefs
import io.olaph.slack.dto.jackson.common.types.Usergroup

fun Usergroup.Companion.sample() = Usergroup(id = "SampleUsergroupID",
        teamId = "SampleTeamID",
        isUsergroup = true,
        name = "SampleUsergroupName",
        description = "SampleUsergroupDescription",
        handle = "SampleUsergroupHandle",
        isExternal = false,
        dateCreate = 0,
        dateUpdate = 0,
        dateDelete = 1,
        autoType = AutoType.NULL,
        createdBy = "SampleCreatorID",
        updatedBy = "SampleUpdaterID",
        deletedBy = "SampleDeleterID",
        prefs = Prefs.sample(),
        users = listOf("SampleUserID1", "SampleUserID2"),
        userCount = 2)

fun Prefs.Companion.sample() = Prefs(listOf("SampleChannelID"), listOf("SampleGroupID"))