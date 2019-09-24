package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.ImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/im.mark
 */
abstract class ImMarkMethod : ApiCallMethod<ImMarkMethod, SuccessfulImMarkResponse, ErrorImMarkResponse, ImMarkRequest>()
