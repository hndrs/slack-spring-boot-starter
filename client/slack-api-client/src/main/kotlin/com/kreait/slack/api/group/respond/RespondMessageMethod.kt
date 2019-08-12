package com.kreait.slack.api.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.SlackRespondMessageRequest
import com.kreait.slack.api.group.ApiCallMethod

abstract class RespondMessageMethod : ApiCallMethod<RespondMessageMethod, Unit, Unit, SlackRespondMessageRequest>()



