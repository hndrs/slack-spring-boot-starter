package io.hndrs.slack.api.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsListResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsListRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsListResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/stars.list
 */
abstract class StarsListMethod :
    ApiCallMethod<StarsListMethod, SuccessfulStarsListResponse, ErrorStarsListResponse, StarsListRequest>()
