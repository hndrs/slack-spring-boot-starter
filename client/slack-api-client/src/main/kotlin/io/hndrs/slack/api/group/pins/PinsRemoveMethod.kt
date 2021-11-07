package io.hndrs.slack.api.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/pins.remove
 */
abstract class PinsRemoveMethod :
    ApiCallMethod<PinsRemoveMethod, SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse, PinsRemoveRequest>()
