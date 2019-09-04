package com.kreait.slack.api.contract.jackson

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun InteractiveMessage.Companion.sample(): InteractiveMessage {
    return InteractiveMessage(InteractiveComponentResponse.Team.sample(), "token", InstantSample.sample(), InstantSample.sample(),
            User.sample(), "",
            Channel.sample(), mapOf(), "", "", listOf(), listOf(), null, "", null)
}

fun Channel.Companion.sample(): Channel {
    return Channel("ChannelId", "ChannelName")
}

fun InteractiveComponentResponse.Team.Companion.sample(): InteractiveComponentResponse.Team {
    return InteractiveComponentResponse.Team("TeamId", "domain", null, null)
}

fun User.Companion.sample(): User {
    return User("UserId", "name", "Username", null)
}
