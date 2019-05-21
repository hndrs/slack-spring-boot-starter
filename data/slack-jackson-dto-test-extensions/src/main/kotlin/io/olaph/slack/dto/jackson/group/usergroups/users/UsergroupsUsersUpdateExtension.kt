package io.olaph.slack.dto.jackson.group.usergroups.users

fun SuccessfulUsergroupUsersUpdateResponse.Companion.sample() = SuccessfulUsergroupUsersUpdateResponse(true, Usergroup.sample())

fun ErrorUsergroupUsersUpdateResponse.Companion.sample() = ErrorUsergroupUsersUpdateResponse(false, "")

fun SlackUsergroupUsersUpdateRequest.Companion.sample() = SlackUsergroupUsersUpdateRequest(
        "SampleUsergroupID",
        listOf("SampleUserID1", "SampleUserID2"),
        2)

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