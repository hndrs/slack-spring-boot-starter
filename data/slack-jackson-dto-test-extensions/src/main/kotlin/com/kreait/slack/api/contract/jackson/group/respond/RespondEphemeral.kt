package com.kreait.slack.api.contract.jackson.group.respond

import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.common.messaging.sample

fun RespondMessageRequest.Companion.sample(): RespondMessageRequest {
    return RespondMessageRequest("", listOf(Attachment.sample()), listOf(), ResponseType.EPHEMERAL)
}
