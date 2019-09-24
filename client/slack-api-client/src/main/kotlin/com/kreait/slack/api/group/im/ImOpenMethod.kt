package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.ImOpenRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/im.open
 */
abstract class ImOpenMethod : ApiCallMethod<ImOpenMethod, SuccessfulImOpenResponse, ErrorImOpenResponse, ImOpenRequest>()
