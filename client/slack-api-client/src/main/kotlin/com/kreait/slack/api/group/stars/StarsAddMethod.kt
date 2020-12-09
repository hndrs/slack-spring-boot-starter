package com.kreait.slack.api.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsAddRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/stars.add
 */
abstract class StarsAddMethod :
    ApiCallMethod<StarsAddMethod, SuccessfulStarsAddResponse, ErrorStarsAddResponse, StarsAddRequest>()
