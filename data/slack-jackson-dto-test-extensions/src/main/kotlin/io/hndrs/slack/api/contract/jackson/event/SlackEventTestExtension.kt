package io.hndrs.slack.api.contract.jackson.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.hndrs.slack.api.contract.jackson.common.InstantSample
import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.common.types.sample
import io.hndrs.slack.api.contract.jackson.event.type.subteam.SubteamCreated
import io.hndrs.slack.api.contract.jackson.event.type.subteam.SubteamMembersChanged
import io.hndrs.slack.api.contract.jackson.event.type.team.TeamJoin
import io.hndrs.slack.api.contract.jackson.event.type.user.UserChange
import io.hndrs.slack.api.contract.jackson.group.usergroups.UserGroup
import io.hndrs.slack.api.contract.jackson.group.usergroups.sample


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

fun SlackEvent.Companion.sample(any: Event): SlackEvent {
    val json = jacksonObjectMapper().writeValueAsString(any)
    val mapValue: Map<String, Any> = jacksonObjectMapper().readValue(json, Map::class.java) as Map<String, Any>
    return SlackEvent(
        "",
        "",
        "",
        "",
        setOf(),
        "",
        0,
        mapValue
    )
}

fun SlackChallenge.Companion.sample(): SlackChallenge {
    return SlackChallenge("", "", "")
}

fun SubteamCreated.Companion.sample(): SubteamCreated {
    return SubteamCreated(TYPE, UserGroup.sample())
}

fun SubteamMembersChanged.Companion.sample(): SubteamMembersChanged {
    return SubteamMembersChanged(
        TYPE, "", InstantSample.sample(), InstantSample.sample(), setOf(), "",
        setOf(), ""
    )
}

fun TeamJoin.Companion.sample(): TeamJoin {
    return TeamJoin(TYPE, Member.sample())
}

fun UserChange.Companion.sample(): UserChange {
    return UserChange(TYPE, Member.sample())
}
