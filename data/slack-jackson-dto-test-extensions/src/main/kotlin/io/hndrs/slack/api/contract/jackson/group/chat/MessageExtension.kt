package io.hndrs.slack.api.contract.jackson.group.chat

import io.hndrs.slack.api.contract.jackson.common.InstantSample
import io.hndrs.slack.api.contract.jackson.common.messaging.Block
import io.hndrs.slack.api.contract.jackson.common.messaging.sample
import io.hndrs.slack.api.contract.jackson.common.types.Message

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
