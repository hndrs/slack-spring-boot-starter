package com.kreait.slack.api.group.respond

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.respond.SlackRespondMessageRequest

abstract class RespondMessageMethod : ApiCallMethod<RespondMessageMethod, Unit, Unit, SlackRespondMessageRequest>()



