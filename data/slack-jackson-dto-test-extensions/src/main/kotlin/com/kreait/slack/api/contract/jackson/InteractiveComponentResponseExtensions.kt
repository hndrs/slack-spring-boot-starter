package com.kreait.slack.api.contract.jackson

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun InteractiveComponentResponse.Companion.sample(): InteractiveComponentResponse {
    return InteractiveComponentResponse("type", "token", InstantSample.sample(), InstantSample.sample(),
            InteractiveComponentResponse.Team.sample(), InteractiveComponentResponse.User.sample(), "",
            InteractiveComponentResponse.Channel.sample(), null, null, null, listOf(), "")
}

fun InteractiveComponentResponse.Channel.Companion.sample(): InteractiveComponentResponse.Channel {
    return InteractiveComponentResponse.Channel("ChannelId", "ChannelName")
}

fun InteractiveComponentResponse.Team.Companion.sample(): InteractiveComponentResponse.Team {
    return InteractiveComponentResponse.Team("TeamId", "domain", null, null)
}

fun InteractiveComponentResponse.User.Companion.sample(): InteractiveComponentResponse.User {
    return InteractiveComponentResponse.User("UserId", "Username", null)
}
