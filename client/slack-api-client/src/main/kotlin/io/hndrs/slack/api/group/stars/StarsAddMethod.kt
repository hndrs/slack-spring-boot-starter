package io.hndrs.slack.api.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsAddResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsAddRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsAddResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/stars.add
 */
abstract class StarsAddMethod :
    ApiCallMethod<StarsAddMethod, SuccessfulStarsAddResponse, ErrorStarsAddResponse, StarsAddRequest>()
