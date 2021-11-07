package io.hndrs.slack.api.group.stars

import io.hndrs.slack.api.contract.jackson.group.stars.ErrorStarsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.stars.StarsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.stars.SuccessfulStarsRemoveResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/stars.list
 */
abstract class StarsRemoveMethod :
    ApiCallMethod<StarsRemoveMethod, SuccessfulStarsRemoveResponse, ErrorStarsRemoveResponse, StarsRemoveRequest>()
