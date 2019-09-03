package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.common.InstantSample
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.messaging.sample

fun Message.Companion.sample(): Message = Message(
        "",
        "",
        "",
        InstantSample.sample(),
        "",
        listOf(),
        listOf(Block.Action.sample()),
        ""
)
