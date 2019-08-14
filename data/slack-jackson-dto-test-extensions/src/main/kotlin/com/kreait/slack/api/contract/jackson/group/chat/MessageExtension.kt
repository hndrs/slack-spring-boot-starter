package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun Message.Companion.sample(): Message = Message(
        "",
        "",
        "",
        Instant.now(),
        "",
        listOf(),
        ""
)
