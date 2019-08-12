package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ImMarkMethod : ApiCallMethod<ImMarkMethod, SuccessfulImMarkResponse, ErrorImMarkResponse, SlackImMarkRequest>()
