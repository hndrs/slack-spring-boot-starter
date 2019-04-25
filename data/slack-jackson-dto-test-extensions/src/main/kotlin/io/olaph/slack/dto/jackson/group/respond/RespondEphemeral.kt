package io.olaph.slack.dto.jackson.group.respond

import io.olaph.slack.dto.jackson.common.messaging.Attachment
import io.olaph.slack.dto.jackson.common.messaging.sample

fun SlackRespondMessageRequest.Companion.sample(): SlackRespondMessageRequest {
    return SlackRespondMessageRequest("", listOf(Attachment.sample()), listOf(), ResponseType.EPHEMERAL)
}