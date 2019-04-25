package io.olaph.slack.client.group.respond

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.respond.SlackRespondMessageRequest

abstract class RespondEphemeralMethod : ApiCallMethod<RespondEphemeralMethod, Unit, Unit, SlackRespondMessageRequest>()



