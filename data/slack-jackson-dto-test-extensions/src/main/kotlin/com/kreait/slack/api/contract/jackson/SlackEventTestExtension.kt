package com.kreait.slack.api.contract.jackson

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.common.types.sample
import com.kreait.slack.api.contract.jackson.event.EventData
import com.kreait.slack.api.contract.jackson.event.SlackChallenge
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.api.contract.jackson.event.SubteamCreated
import com.kreait.slack.api.contract.jackson.event.SubteamMembersChanged
import com.kreait.slack.api.contract.jackson.event.TeamJoin
import com.kreait.slack.api.contract.jackson.event.UserChange
import com.kreait.slack.api.contract.jackson.group.usergroups.UserGroup
import com.kreait.slack.api.contract.jackson.group.usergroups.sample

fun SlackEvent.Companion.sample(event: Map<String, Any>): SlackEvent {
    return SlackEvent(
            "event_callback",
            "",
            "",
            "",
            setOf(),
            "",
            0,
            event
    )
}

fun SlackEvent.Companion.sample(event: EventData): SlackEvent {
    return SlackEvent.sample(event.toMap())
}

fun SlackChallenge.Companion.sample(): SlackChallenge {
    return SlackChallenge("", "", "")
}

fun SubteamCreated.Companion.sample(): SubteamCreated {
    return SubteamCreated(UserGroup.sample())
}

fun SubteamMembersChanged.Companion.sample(): SubteamMembersChanged {
    return SubteamMembersChanged("", InstantSample.sample(), InstantSample.sample(), setOf(), "",
            setOf(), "")
}

fun TeamJoin.Companion.sample(): TeamJoin {
    return TeamJoin(Member.sample())
}

fun UserChange.Companion.sample(): UserChange {
    return UserChange(Member.sample())
}
