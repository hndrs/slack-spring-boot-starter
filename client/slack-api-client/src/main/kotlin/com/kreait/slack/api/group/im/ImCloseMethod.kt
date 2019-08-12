package com.kreait.slack.api.group.im

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse

abstract class ImCloseMethod : ApiCallMethod<ImCloseMethod, SuccessfulImCloseResponse, ErrorImCloseResponse, SlackImCloseRequest>()
