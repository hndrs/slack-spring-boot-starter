package com.kreait.slack.api.contract.jackson.group.respond

import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.common.messaging.sample

fun SlackRespondMessageRequest.Companion.sample(): SlackRespondMessageRequest {
    return SlackRespondMessageRequest("", listOf(Attachment.sample()), listOf(), ResponseType.EPHEMERAL)
}
