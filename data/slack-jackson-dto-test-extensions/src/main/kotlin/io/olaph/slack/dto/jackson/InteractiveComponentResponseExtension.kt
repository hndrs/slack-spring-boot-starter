package io.olaph.slack.dto.jackson

import io.olaph.slack.dto.jackson.common.Action
import io.olaph.slack.dto.jackson.group.dialog.Channel
import io.olaph.slack.dto.jackson.group.dialog.InteractiveComponentResponse
import io.olaph.slack.dto.jackson.group.dialog.Team
import io.olaph.slack.dto.jackson.group.dialog.User

fun InteractiveComponentResponse.Companion.sample(): InteractiveComponentResponse {
    return InteractiveComponentResponse(
            "",
            "",
            "",
            "",
            Team.sample(),
            User.sample(),
            "",
            Channel.sample(),
            mapOf(),
            "",
            "",
            listOf(Action.sample()),
            ""

    )
}

fun Action.Companion.sample(): Action {
    return Action("", "", Action.Style.DEFAULT, Action.ActionType.BUTTON, "", listOf(Action.Option.sample()), listOf(Action.Option.sample()))
}

fun Action.Option.Companion.sample(): Action.Option {
    return Action.Option("", "")
}

fun Channel.Companion.sample(): Channel {
    return Channel("", "")
}

fun User.Companion.sample(): User {
    return User("", "", "")
}

fun Team.Companion.sample(): Team {
    return Team("", "", "", "")
}
