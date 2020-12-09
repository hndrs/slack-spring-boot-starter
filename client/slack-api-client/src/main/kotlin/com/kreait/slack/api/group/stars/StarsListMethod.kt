package com.kreait.slack.api.group.stars

import com.kreait.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import com.kreait.slack.api.contract.jackson.group.stars.StarsListRequest
import com.kreait.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/stars.list
 */
abstract class StarsListMethod :
    ApiCallMethod<StarsListMethod, SuccessfulStarsListResponse, ErrorStarsListResponse, StarsListRequest>()
