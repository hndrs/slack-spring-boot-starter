package io.hndrs.slack.api.group.respond

import io.hndrs.slack.api.contract.jackson.group.respond.RespondMessageRequest
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * to respond to a request
 * https://api.slack.com/interactive-messages
 */
abstract class RespondMessageMethod : io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.respond.RespondMessageMethod, Unit, Unit, RespondMessageRequest>()



