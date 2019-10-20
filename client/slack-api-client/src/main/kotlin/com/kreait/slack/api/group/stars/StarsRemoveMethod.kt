package com.kreait.slack.api.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsRemoveRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/stars.list
 */
abstract class StarsRemoveMethod : ApiCallMethod<StarsRemoveMethod, SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse, StarsRemoveRequest>()
