package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.ImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/im.close
 */
abstract class ImCloseMethod : ApiCallMethod<ImCloseMethod, SuccessfulImCloseResponse, ErrorImCloseResponse, ImCloseRequest>()
