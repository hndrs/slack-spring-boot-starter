package io.olaph.slack.dto.jackson

fun InteractiveComponentResponse.Companion.sample(): InteractiveComponentResponse {
    return InteractiveComponentResponse("type", "token", "", "",
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
