package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.group.chat.sample

fun Group.Companion.sample(): Group {
    return Group(
        InstantSample.sample(), "", "", false, true, false, InstantSample.sample(), Message.sample(),
        listOf(), "", Purpose.sample(), Topic.sample(), 0, 0
    )
}
