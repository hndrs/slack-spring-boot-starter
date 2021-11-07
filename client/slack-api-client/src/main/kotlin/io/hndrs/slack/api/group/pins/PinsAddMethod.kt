package io.hndrs.slack.api.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsAddRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/pins.add
 */
abstract class PinsAddMethod :
    ApiCallMethod<PinsAddMethod, SuccessfulPinsAddResponse, ErrorPinsAddResponse, PinsAddRequest>()
