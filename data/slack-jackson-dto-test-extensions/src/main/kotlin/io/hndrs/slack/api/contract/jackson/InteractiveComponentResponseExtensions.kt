package io.hndrs.slack.api.contract.jackson

import io.hndrs.slack.api.contract.jackson.common.InstantSample
import io.hndrs.slack.api.contract.jackson.common.types.Message
import io.hndrs.slack.api.contract.jackson.group.chat.sample

fun InteractiveMessage.Companion.sample(): InteractiveMessage {
    return InteractiveMessage(
        InteractiveComponentResponse.Team.sample(), "token", InstantSample.sample(), InstantSample.sample(),
        User.sample(), "",
        Channel.sample(), mapOf(), "", "", listOf(), Message.sample(), listOf(), null, "", null
    )
}


fun BlockActions.Companion.sample(): BlockActions {
    return BlockActions(
        InteractiveComponentResponse.Team.sample(), User.sample(), "token", "", null, "",
        Channel.sample(), "", listOf()
    )
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
