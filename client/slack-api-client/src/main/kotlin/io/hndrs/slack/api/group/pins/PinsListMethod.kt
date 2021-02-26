package io.hndrs.slack.api.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsListRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/pins.list
 */
abstract class PinsListMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.pins.PinsListMethod, SuccessfulPinsListResponse, ErrorPinsListResponse, PinsListRequest>()
