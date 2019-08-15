package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun Message.Companion.sample(): Message = Message(
        "",
        "",
        "",
        InstantSample.sample(),
        "",
        listOf(),
        ""
)
