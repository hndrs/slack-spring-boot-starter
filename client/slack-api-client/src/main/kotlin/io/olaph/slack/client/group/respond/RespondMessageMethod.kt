package io.olaph.slack.client.group.respond

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.respond.SlackRespondMessageRequest

abstract class RespondMessageMethod : ApiCallMethod<RespondMessageMethod, Unit, Unit, SlackRespondMessageRequest>()



