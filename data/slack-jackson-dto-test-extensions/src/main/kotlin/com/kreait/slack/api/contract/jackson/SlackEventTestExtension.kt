package com.kreait.slack.api.contract.jackson

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.common.types.sample
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackChallenge
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.api.contract.jackson.group.usergroups.UserGroup
import com.kreait.slack.api.contract.jackson.group.usergroups.sample


fun SlackEvent.Companion.sample(): SlackEvent {
    return SlackEvent(
            "",
            "",
            "",
            "",
            setOf(),
            "",
            0,
            mapOf()
    )
}

fun SlackChallenge.Companion.sample(): SlackChallenge {
    return SlackChallenge("", "", "")
}


fun Event.SubteamCreated.Companion.sample(): Event.SubteamCreated {
    return Event.SubteamCreated(TYPE, UserGroup.sample())
}

fun Event.SubteamMembersChanged.Companion.sample(): Event.SubteamMembersChanged {
    return Event.SubteamMembersChanged(TYPE, "", InstantSample.sample(), InstantSample.sample(), setOf(), "",
            setOf(), "")
}

fun Event.TeamJoin.Companion.sample(): Event.TeamJoin {
    return Event.TeamJoin(TYPE, Member.sample())
}

fun Event.UserChange.Companion.sample(): Event.UserChange {
    return Event.UserChange(TYPE, Member.sample())
}
