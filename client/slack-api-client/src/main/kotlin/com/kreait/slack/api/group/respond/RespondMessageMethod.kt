package com.kreait.slack.api.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * to respond to a request
 * https://api.slack.com/interactive-messages
 */
abstract class RespondMessageMethod : ApiCallMethod<RespondMessageMethod, Unit, Unit, RespondMessageRequest>()



