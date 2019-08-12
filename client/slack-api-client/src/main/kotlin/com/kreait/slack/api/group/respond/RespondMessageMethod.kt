package com.kreait.slack.api.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.group.ApiCallMethod

abstract class RespondMessageMethod : ApiCallMethod<RespondMessageMethod, Unit, Unit, RespondMessageRequest>()



