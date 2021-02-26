package io.hndrs.slack.api.contract.jackson.group.respond

import io.hndrs.slack.api.contract.jackson.common.messaging.Attachment
import io.hndrs.slack.api.contract.jackson.common.messaging.sample

fun RespondMessageRequest.Companion.sample(): RespondMessageRequest {
    return RespondMessageRequest("", listOf(Attachment.sample()), listOf(), ResponseType.EPHEMERAL)
}
