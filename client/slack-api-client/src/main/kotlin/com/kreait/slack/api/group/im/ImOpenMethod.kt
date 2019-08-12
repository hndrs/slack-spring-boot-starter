package com.kreait.slack.api.group.im

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImOpenRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse

abstract class ImOpenMethod : ApiCallMethod<ImOpenMethod, SuccessfulImOpenResponse, ErrorImOpenResponse, SlackImOpenRequest>()
